# -*- coding: UTF-8 -*-

from math import sqrt


# 欧几里得距离
def sim_distance(prefs, person1, person2):
    si = {}
    for item in prefs[person1]:
        if item in prefs[person2]:
            si[item] = 1

    if len(si) == 0:
        return 0
    sum_of_squares = sum(
            [pow(prefs[person1][item] - prefs[person2][item], 2) for item in prefs[person1] if item in prefs[person2]])
    return 1 / (1 + sqrt(sum_of_squares))


# 皮尔逊系数
def sim_person(prefs, person1, person2):
    si = {}
    for item in prefs[person1]:
        if item in prefs[person2]:
            si[item] = 1
    n = len(si)
    if n == 0:
        return 1
    sum_x = sum([prefs[person1][it] for it in si])
    sum_y = sum([prefs[person2][it] for it in si])
    sum_xsq = sum([pow(prefs[person1][it], 2) for it in si])
    sum_ysq = sum([pow(prefs[person2][it], 2) for it in si])
    p_sum = sum([prefs[person1][it] * prefs[person2][it] for it in si])
    num = p_sum - sum_x * sum_y / n
    den = sqrt((sum_xsq - pow(sum_x, 2) / n) * (sum_ysq - pow(sum_y, 2) / n))
    if den == 0:
        return 0
    return num / den


# sum(sim * rating) / sum(sim)
def get_recommendations(prefers, person, n=10, similarity=sim_person):
    totals = {}
    sim_sum = {}
    for other in prefers:
        if other == person:
            continue
        sim = similarity(prefers, person, other)
        if sim < 0:
            continue
        for item in prefers[other]:
            if item not in prefers[person] or prefers[person][item] == 0:
                totals.setdefault(item, 0)
                totals[item] += prefers[other][item] * sim
                sim_sum.setdefault(item, 0)
                sim_sum[item] += sim
    rankings = [(total / sim_sum[item], item) for item, total in totals.items()]
    rankings.sort()
    rankings.reverse()
    return rankings[0:n]
