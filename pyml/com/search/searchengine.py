# -*- coding: UTF-8 -*-

import urllib2
from bs4 import BeautifulSoup
from urlparse import urljoin
import sqlite3
import re

ignore_words = set(['the', 'to', 'of', 'and', 'a', 'in', 'is', 'it'])


class Crawler:
    def __init__(self, dbname):
        self.con = sqlite3.connect(dbname)

    def __del__(self):
        self.con.close()

    def db_commit(self):
        self.con.commit()

    # 获取id ,如果不存在，加入数据库
    def get_entryid(self, table, field, value, createnew=True):
        cur = self.con.execute("select rowid from %s where %s='%s'" % (table, field, value))
        res = cur.fetchone()
        if res is None:
            cur = self.con.execute("insert into %s (%s) values ('%s')" % (table, field, value))
            return cur.lastrowid
        else:
            return res[0]

    # 建索引
    def add_to_index(self, url, soup):
        if self.is_indexed(url):
            return
        print 'Indexing ' + url
        text = self.get_text_only(soup)
        words = self.get_saperate_words(text)
        url_id = self.get_entryid('urllist', 'url', url)
        for i in range(len(words)):
            word = words[i]
            if word in ignore_words: continue
            word_id = self.get_entryid('wordlist', 'word', word)
            self.con.execute("insert into wordlocation(urlid,wordid,location) values (%d, %d, %d)" % (url_id, word_id, i))

    # 从html网页中获取文字
    def get_text_only(self, soup):
        v = soup.string
        if v is None:
            c = soup.contents
            result_text = ''
            for t in c:
                subtext = self.get_text_only(t)
                result_text += subtext + '\n'
            return result_text
        else:
            return v.strip()

    # 分词处理
    def get_saperate_words(self, text):
        splitter = re.compile('\\W*')
        return [s.lower() for s in splitter.split(text) if s != '']

    def is_indexed(self, url):
        u = self.con.execute("select rowid from urllist where url = '%s'" % url).fetchone()
        if u is not None:
            v = self.con.execute("select * from wordlocation where urlid = '%s'" % url)
            if v is not None:
                return True
        return False

    def add_link_ref(self, urlFrom, urlTo, linkText):
        pass

    def crawl(self, pages, depth=2):
        for i in range(depth):
            new_pages = set()
            for page in pages:
                try:
                    c = urllib2.urlopen(page)
                except:
                    print "Could not open %s " % page
                    continue
                soup = BeautifulSoup(c.read())
                self.add_to_index(page, soup)
                links = soup('a')
                for link in links:
                    if 'href' in dict(link.attrs):
                        url = urljoin(page, link['href'])
                        if url.find("'") != -1:
                            continue
                        url = url.split("#")[0]
                        if url[0:4] == 'http' and not self.is_indexed(url):
                            new_pages.add(url)
                        link_text = self.get_text_only(link)
                        self.add_link_ref(page, url, link_text)
                self.db_commit()
            pages = new_pages

    def create_index_tables(self):
        self.con.execute('create table urllist(url)')
        self.con.execute('create table wordlist(word)')
        self.con.execute('create table wordlocation(urlid,wordid,location)')
        self.con.execute('create table link(fromid integer,toid integer)')
        self.con.execute('create table linkwords(wordid,linkid)')
        self.con.execute('create index wordidx on wordlist(word)')
        self.con.execute('create index urlidx on urllist(url)')
        self.con.execute('create index wordurlidx on wordlocation(wordid)')
        self.con.execute('create index urltoidx on link(toid)')
        self.con.execute('create index urlfromidx on link(fromid)')
        self.dbcommit()
