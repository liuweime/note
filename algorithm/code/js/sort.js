/*
* @Author: Marte
* @Date:   2018-07-30 21:12:45
* @Last Modified by:   Marte
* @Last Modified time: 2018-07-30 22:41:17
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
        });
    },

    /**
     * 插入排序
     * TODO 动画未完成
     * @param  array list 待排序队列
     * @return
     */
    insert_sort(list) {

        const len = list.length;

        let i = 1;
        let a = i;
        let t = setInterval(function() {
            if (i < len) {

                let tmp = list[i];
                let j = i;
                let tj = j;
                let left = 0;
                let position = [];
                for (; j > 0 && list[j-1] >= tmp; j--) {
                    list[j] = list[j-1];
                    position[j] = $('span').eq(j).position().left;
                    left = $('span').eq(j-1).position().left;
                }

                a = i + position.length;
                for(let attr in position) {
                    $('span').eq(attr-1).animate({"left": position[attr]}, 500);
                }

                if(i == 2) {
                    clearInterval(t);
                    return false;
                }

                $('span').eq(i).animate({"left": left}, 500);
                list[j] = tmp;
                i++;
            } else {
                clearInterval(t);
                console.log(list);
            }

        }, 500*a);
    },
}
