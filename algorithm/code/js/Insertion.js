import SortInterface from './SortInterface.js';
import {exchNode} from './Util.js';
export default class Insertion extends SortInterface {

    aNode = [];
    sTargetColor = '#FBC02D';
    sNormalColor = '#283593';

    async sort(list) {
        const length = list.length;
        for (let i = 0; i < list.length; i++) {
            this.aNode[i] = $('span').eq(i);
        }

        for (let i = 1; i < length; i++) {

            let oTmpNode = this.aNode[i];

            let iPointer = i;
            let flag = false;
            let iLeft = 0;
            let iPrevLeft = 0;
            const that = this;
            while (iPointer > 0 && super.less(oTmpNode.attr('num'), this.aNode[iPointer-1].attr('num'))) {

                if (false === flag) {
                    oTmpNode.addClass('dashed-box');
                    flag = true;
                    await super.sleep(500);
                }

                let oPrevNode = this.aNode[iPointer-1];
                // 保存节点位置
                iLeft = oPrevNode.position().left;
                // 获取上一个节点的位置
                let iTmpLeft = iPrevLeft === 0 ? oTmpNode.position().left : iPrevLeft;

                // 移动节点
                oPrevNode.css("background", this.sTargetColor);
                oPrevNode.animate({"left": iTmpLeft}, 500, () => {
                    oPrevNode.css("background", that.sNormalColor);
                });
                await super.sleep(500);

                this.aNode[iPointer] = this.aNode[iPointer-1];
                iPrevLeft = iLeft;
                --iPointer;
            }

            // 被标记节点移动
            if (iLeft) {
                oTmpNode.css("background", this.sTargetColor);
                oTmpNode.animate({"left": iLeft, "bottom": 30}, 500, () => {
                    oTmpNode.css("background", that.sNormalColor);
                });
                await super.sleep(500);

                oTmpNode.removeClass('dashed-box');
            }

            this.aNode[iPointer] = oTmpNode;
            await super.sleep(500);
        }
    }
}
