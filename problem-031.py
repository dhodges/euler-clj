#!/usr/bin/env python

import os
import sys
import unittest

# ------------------------------------------------------------------

def main():
    pass

# ------------------------------------------------------------------

# see: http://home.att.net/~numericana/answer/numbers.htm#partitions

# input m
# dim p(m)
# p(0) = 1

# for i = 1 to m
#   j=1 : k=1 : s=0
#   while j>0
#     j = i-(3*k*k+k)\2
#     if j>=0 then s = s - (-1)^k*P(j)
#     j = i-(3*k*k-k)\2
#     if j>=0 then s = s - (-1)^k*P(j)
#     k = k+1
#   wend
#   p(i) = s
# next i

def partition_a(m):
    p = range(m)
    for i in range(m):
        j = 1
        k = 1
        s = 0
        while j > 0:
            j = i - (3*k*k+k)/2
            if j >= 0:
                s = s - (-1^k) * p[j-1]
            j = i-(3*k*k-k)/2
            if j >= 0:
                s = s - (-1^k) * p[j-1]
            k = k+1
        p[i] = s
    return p

# =======

# INPUT m
# DIM a(m),p(m)
# FOR i = 0 TO m: p(i) = 1: NEXT i

# FOR u = 2 TO m
#   FOR i = 0 TO m: a(i) = p(i): p(i) = 0: NEXT i
#   FOR j = 0 TO m STEP u
#     FOR k = j TO m
#       p(k) = p(k) + a(k-j)
#     NEXT k
#   NEXT j
# NEXT u

# REM At this point, p(n) is the number of partitions of n
# REM (for any n between 0 and m).

def partition_b(m):
    a = range(m)
    p = range(m)
    for i in range(m):
        p[i] = 1

    for u in range(2, m+1):
        for i in range(m):
            a[i] = p[i]
            p[i] = 0
        for j in range(0, m, u):
            for k in range(j, m):
                p[k] = p[k] + a[k-j]
        print p

    return p


# see: http://www.site.uottawa.ca/~ivan/F49-int-part.pdf
#
# Algorithm ZSI
# for i <- I to r do x;<- l;
# xt?n; m-l; ft- l; output
# xr;
#
# 326 A. ZOGHBI AND I. STOJMENOVId
# while x1l I do {
# if x1,
# : 2 then
# {m * m * l; x1,+-
# l; h <- h - l}
# else
# {r +xh l; t+m-h+ l; xh<-r
# while
# l) r do {h- h * 1; x6+ r; t <- t - r}
# ifl:0thenm+-h
# else{rr+h+l:
# if t > I then {l * h + l; 4+7}}
# OUtpUt
# Xt,Xz,...,X^j

def partition_zs1(n):
    x = range(n)
    for i in range(n):
        x[i] = 1
    x[0] = n
    m = 1
    h = 1
    print x[:1]
    while x[0] != 1:
        if x[h-1] == 2:
            m = m +1
            x[h-1] = 1
            h = h - 1
        else:
            r = x[h-1] - 1
            t = m - h + 1
            x[h-1] = r
            while t >= r:
                h = h + 1
                x[h-1] = r
                t = t - r
            if t == 0:
                m = h
            else:
                m = h + 1
                if t > 1:
                    h = h + 1
                    x[h-1] = t
        ndx   = 0
        total = 0
        while total < n:
            total += x[ndx]
            ndx += 1

        print x[:ndx]

# ------------------------------------------------------------------

def min_above_1(seq):
    return min(filter(lambda x:x>1, seq))


def partitions(n):
    partition = [n]
    print partition
    while max(partition) > 1:




# ------------------------------------------------------------------

if __name__ == "__main__":
    partition_zs1(5)


