/*
* @Author: liuwei
* @Date:   2018-07-30 21:12:45
* @Last Modified by:   liuwei
* @Last Modified time: 2019-04-04 11:19:14
*/

'use strict';

export default {


    /**
     * 选择排序
     * @param  array data 待排序数组
     * @return
     */
    select_sort(data) {
        const that = this;
        let i = 0;
        let t = setInterval(function() {

            if (i < data.length) {
                let min = i;
                for(let j = i+1; j < data.length; j++) {
                    if (data[min] > data[j]) {
                        min = j;
                    }
                }
                that.exchange(i, min);

                let tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;

                i++;
            } else {
                clearInterval(t);
            }
        }, 1000);
    },

    /**
     * 呼唤dom节点
     * @param  Object ori_node    原节点
     * @param  Object target_node 目标节点
     * @return
     */
    exchange(ori_node, target_node) {
        let spanmin = $('span').eq(target_node);
        let spani = $('span').eq(ori_node);

        spani.css('background','#e87d7d');
        spanmin.css('background','#e87d7d');

        spani.animate({'left':spanmin.position().left},500, function() {
            spani.css('background','#333')
        })
        spanmin.animate({'left':spani.position().left},500, function() {
            spanmin.css('background','#333')
            let b = spani.clone(true);
            let a = spanmin.replaceWith(b);
            spani.replaceWith(a);

            //flag = true;
        });
    },

    /**
     * 插入排序
     *
     * @param  array list 待排序队列
     * @return
     */
    insert_sort(list) {

        let dom = [];
        for (let i = 0; i < list.length; i++) {
            dom[i] = $('span').eq(i);
        }
        const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));

        return (async function() {
            const len = list.length;

            for(let i = 1; i < len; i++) {
                let tmp = list[i];
                let tmpdom = dom[i];

                let j = i;
                let left = 0;
                let prevLeft = 0;
                let flag = false;
                while(j > 0 && list[j-1] >= tmp) {
                    if (false === flag) {
                        tmpdom.css('background','#e87d7d');
                        tmpdom.animate({"bottom": -dom[j].height()}, 500, function() {
                            tmpdom.css('background','#333')
                        });
                        flag = true;

                        await sleep(500);
                    }

                    list[j] = list[j-1];

                    left = dom[j-1].position().left;
                    let tmpLeft = prevLeft == 0? dom[j].position().left : prevLeft;

                    dom[j-1].css('background','#e87d7d');
                    let mm = dom[j-1];
                    mm.animate({"left": tmpLeft}, 500, function() {
                        mm.css('background','#333')
                    });

                    await sleep(500);
                    dom[j] = dom[j-1];
                    prevLeft = left;
                    j--;
                }


                if (left) {
                    tmpdom.css('background','#e87d7d');
                    tmpdom.animate({"left": left, "bottom":0}, 500, function() {
                        tmpdom.css('background','#333')
                    });
                    await sleep(500);
                }
                dom[j] = tmpdom;
                list[j] = tmp;
            }

            alert('success')
        })();
    },

    /**
     * 冒泡排序
     * @param {array} list 待排序队列
     */
    bubble_sort(list)
    {
        const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));

        let that = this;
        (async function() {
            for(let i = 0; i < list.length-1; i++) {

                for (let j = 0; j < list.length-1-i; j++) {
                    if (list[j] > list[j+1]) {

                        that.exchange(j,j+1);
                        let tmp = list[j];
                        list[j] = list[j+1];
                        list[j+1] = tmp;
                        await sleep(1000);
                    }
                }
            }
        })();
    },

    /**
     * 希尔排序
     * 步长 选择 h = (3^k-1)/2
     *
     */
    shell_sort(list)
    {
        const length = list.length;

        let dom = [];
        for (let i = 0; i < length; i++) {
            dom[i] = $('span').eq(i);
        }
        const sleep = ms => new Promise(resolve => setTimeout(resolve, ms));

        // 步长计算 使用kauth算法
        let gep = 1;
        while (gep < length/3) {
            gep = gep * 3 + 1;
        }


        (async function() {
            while (gep > 0) {

                $('#text').text('gep:' + gep);
                // 插入排序
                for (let i = gep; i < length; i++) {
                    let tmp = list[i];
                    let tmpdom = dom[i];
                    let j = i;

                    let left = 0;
                    let prevLeft = 0;
                    let flag = false;
                    for (; j >= gep && tmp < list[j-gep]; j -= gep) {


                        if (false === flag) {
                            tmpdom.css('background','#e87d7d');
                            tmpdom.animate({"bottom": -dom[j].height()}, 500, function() {
                                tmpdom.css('background','#333')
                            });
                            flag = true;

                            await sleep(500);
                        }

                        list[j] = list[j-gep];

                        left = dom[j-gep].position().left;
                        let tmpLeft = prevLeft == 0? dom[j].position().left : prevLeft;

                        dom[j-gep].css('background','#e87d7d');
                        let mm = dom[j-gep];
                        mm.animate({"left": tmpLeft}, 500, function() {
                            mm.css('background','#333')
                        });

                        await sleep(500);
                        dom[j] = dom[j-gep];
                        prevLeft = left;

                    }

                    if (left) {
                        tmpdom.css('background','#e87d7d');
                        tmpdom.animate({"left": left, "bottom":0}, 500, function() {
                            tmpdom.css('background','#333')
                        });
                        await sleep(500);
                    }
                    dom[j] = tmpdom;
                    list[j] = tmp;
                }

                gep = parseInt(gep / 3);
                $('#output').find('span').css('background', '#33333382');
            }

            alert('success');
        })();
    },


    /**
     * 归并排序
     *
     */
    merge_sort(list)
    {

    }

}
