# -*- coding: UTF-8 -*-

import Recommendations
import sys


def load_movie(path):
    movies = {}
    for line in open(path + "/movies.dat"):
        (movie_id, title) = line.split("::")[0: 2]
        movies[movie_id] = title
    prefers = {}
    for line in open(path + "/ratings.dat"):
        (user, movie_id, ratings, ts) = line.split("::")
        prefers.setdefault(user, {})
        prefers[user][movies[movie_id]] = float(ratings)
    return prefers


def main(argv=None):
    if argv is None:
        argv = sys.argv
    path = "/home/spark/Documents/10M100K"
    prefers = load_movie(path)


if __name__ == "__main__":
    sys.exit(main())
