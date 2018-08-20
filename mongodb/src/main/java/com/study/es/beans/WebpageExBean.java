package com.study.es.beans;

import java.io.Serializable;

/**
 * Created by spark on 4/19/18.
 */
public class WebpageExBean implements Serializable {
    private static final long serialVersionUID = 1246725947770917955L;
    private String _id;//数据id
    private String parent_id; //上一级任务数据id
    private String task_id; //下载任务id
    private String webpage_url; //链接
    private String title; //标题
    private String short_title; //短标题
    private String keywords; //关键词
    private String web_summary; //摘要
    private String content; //正文（带标签）
    private String no_tag_content; //正文内容，去标签
    private String webpage_code; //新闻编码
    private String classification; //原始分类
    private Integer news_crawl_type; //新闻分类：0普通新闻1头条2热点排行3组图4微博5微信6app7评论
    private String source_report; //发布来源
    private String release_datetime; //发布时间
    private String source_crawl; //采集来源
    private String crawl_datetime; //采集时间
    private String region; //区域
    private Integer reposts_num; //转发次数
    private Integer comments_num; //评论次数
    private Integer clicking_num; //点击次数
    private Integer participate_num; //参与次数
    private Integer vote; //点赞次数
    private Integer against; //反对次数
    private Integer browse_num; //浏览次数
    private String author; //作者
    private String editor; //编辑
    //	private List<VideoUrlBean> video_url_list;
    private String original_id; //新闻原始id，例如weibo_id
    private String original_parent_id; //新闻原始父id，例如：weibo_root_id
    private String original_relation_id; //关联id，如weibo_pid
    private Integer image_status; //是否包含图片：0：不包含1：包含2：组图
    private Integer is_deleted;//否删除,0没删，1删除
    private String metatags; //meta信息
    private String meta_info_key;//网站website	微信biz号/微博号
    private String wechat_account;//微信账号
    private String wechat_Name;//微信名称 /微博名称
    private Integer video_status; //视频状态：0或null不含视频，1独立视频2嵌入视频
    private String pic_path; //视频图片路径
    private  WebpageExBean retweeted; //转载微博内容
    private String label; //历史上今天的标签；事件/历史/出生/逝世/节假日/纪念日

    public Integer getVideo_status() {
        return video_status;
    }

    public void setVideo_status(Integer video_status) {
        this.video_status = video_status;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public WebpageExBean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(WebpageExBean retweeted) {
        this.retweeted = retweeted;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getParent_id() {
        return parent_id;
    }
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    public String getTask_id() {
        return task_id;
    }
    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
    public String getWebpage_url() {
        return webpage_url;
    }
    public void setWebpage_url(String webpage_url) {
        this.webpage_url = webpage_url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShort_title() {
        return short_title;
    }
    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getWeb_summary() {
        return web_summary;
    }
    public void setWeb_summary(String web_summary) {
        this.web_summary = web_summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getNo_tag_content() {
        return no_tag_content;
    }
    public void setNo_tag_content(String no_tag_content) {
        this.no_tag_content = no_tag_content;
    }
    public String getWebpage_code() {
        return webpage_code;
    }
    public void setWebpage_code(String webpage_code) {
        this.webpage_code = webpage_code;
    }
    public String getClassification() {
        return classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }
    public String getSource_report() {
        return source_report;
    }
    public void setSource_report(String source_report) {
        this.source_report = source_report;
    }
    public String getRelease_datetime() {
        return release_datetime;
    }
    public void setRelease_datetime(String release_datetime) {
        this.release_datetime = release_datetime;
    }
    public String getSource_crawl() {
        return source_crawl;
    }
    public void setSource_crawl(String source_crawl) {
        this.source_crawl = source_crawl;
    }
    public String getCrawl_datetime() {
        return crawl_datetime;
    }
    public void setCrawl_datetime(String crawl_datetime) {
        this.crawl_datetime = crawl_datetime;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public Integer getReposts_num() {
        return reposts_num;
    }
    public void setReposts_num(Integer reposts_num) {
        this.reposts_num = reposts_num;
    }
    public Integer getComments_num() {
        return comments_num;
    }
    public void setComments_num(Integer comments_num) {
        this.comments_num = comments_num;
    }
    public Integer getClicking_num() {
        return clicking_num;
    }
    public void setClicking_num(Integer clicking_num) {
        this.clicking_num = clicking_num;
    }
    public Integer getParticipate_num() {
        return participate_num;
    }
    public void setParticipate_num(Integer participate_num) {
        this.participate_num = participate_num;
    }
    public Integer getVote() {
        return vote;
    }
    public void setVote(Integer vote) {
        this.vote = vote;
    }
    public Integer getAgainst() {
        return against;
    }
    public void setAgainst(Integer against) {
        this.against = against;
    }
    public Integer getBrowse_num() {
        return browse_num;
    }
    public void setBrowse_num(Integer browse_num) {
        this.browse_num = browse_num;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getOriginal_id() {
        return original_id;
    }
    public void setOriginal_id(String original_id) {
        this.original_id = original_id;
    }
    public String getOriginal_parent_id() {
        return original_parent_id;
    }
    public void setOriginal_parent_id(String original_parent_id) {
        this.original_parent_id = original_parent_id;
    }
    public String getOriginal_relation_id() {
        return original_relation_id;
    }
    public void setOriginal_relation_id(String original_relation_id) {
        this.original_relation_id = original_relation_id;
    }

    public Integer getImage_status() {
        return image_status;
    }

    public void setImage_status(Integer image_status) {
        this.image_status = image_status;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }
    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getEditor() {
        return editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getMetatags() {
        return metatags;
    }

    public void setMetatags(String metatags) {
        this.metatags = metatags;
    }

    public Integer getNews_crawl_type() {
        return news_crawl_type;
    }

    public void setNews_crawl_type(Integer news_crawl_type) {
        this.news_crawl_type = news_crawl_type;
    }

    public String getMeta_info_key() {
        return meta_info_key;
    }

    public void setMeta_info_key(String meta_info_key) {
        this.meta_info_key = meta_info_key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWechat_account() {
        return wechat_account;
    }

    public void setWechat_account(String wechat_account) {
        this.wechat_account = wechat_account;
    }

    public String getWechat_Name() {
        return wechat_Name;
    }

    public void setWechat_Name(String wechat_Name) {
        this.wechat_Name = wechat_Name;
    }

    @Override
    public String toString() {
        return "WebpageExBean{" +
                "_id='" + _id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", webpage_url='" + webpage_url + '\'' +
                ", title='" + title + '\'' +
                ", short_title='" + short_title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", web_summary='" + web_summary + '\'' +
                ", content='" + content + '\'' +
                ", no_tag_content='" + no_tag_content + '\'' +
                ", webpage_code='" + webpage_code + '\'' +
                ", classification='" + classification + '\'' +
                ", news_crawl_type=" + news_crawl_type +
                ", source_report='" + source_report + '\'' +
                ", release_datetime='" + release_datetime + '\'' +
                ", source_crawl='" + source_crawl + '\'' +
                ", crawl_datetime='" + crawl_datetime + '\'' +
                ", region='" + region + '\'' +
                ", reposts_num=" + reposts_num +
                ", comments_num=" + comments_num +
                ", clicking_num=" + clicking_num +
                ", participate_num=" + participate_num +
                ", vote=" + vote +
                ", against=" + against +
                ", browse_num=" + browse_num +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", original_id='" + original_id + '\'' +
                ", original_parent_id='" + original_parent_id + '\'' +
                ", original_relation_id='" + original_relation_id + '\'' +
                ", image_status=" + image_status +
                ", is_deleted=" + is_deleted +
                ", metatags='" + metatags + '\'' +
                ", meta_info_key='" + meta_info_key + '\'' +
                ", wechat_account='" + wechat_account + '\'' +
                ", wechat_Name='" + wechat_Name + '\'' +
                ", video_status=" + video_status +
                ", pic_path='" + pic_path + '\'' +
                ", retweeted=" + retweeted +
                ", label='" + label + '\'' +
                '}';
    }
}
