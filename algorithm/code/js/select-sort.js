/*
* @Author: Marte
* @Date:   2018-07-25 17:39:42
* @Last Modified by:   Marte
* @Last Modified time: 2018-07-27 16:28:59
*/

'use strict';

export default {
    mysort(data) {

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

        return data;
    },

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
    }
}