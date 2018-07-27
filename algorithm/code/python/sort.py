# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2018-07-25 09:42:32
# @Last Modified by:   Marte
# @Last Modified time: 2018-07-25 11:17:16


def mysort(data):

    length = len(data)
    for i in range(length):
        min = i

        for j in range(i+1, length):
            if data[min] > data[j]:
                min = j

        data[i],data[min] = data[min],data[i]

    return data

eval = input('please input sort numbers:')
data = eval.split(',')
data = [int(num) for num in data]
print('before sort: {}'.format(data))

data = mysort(data)
print('after sort: {}'.format(data))
