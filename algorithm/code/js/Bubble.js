import SortInterface from './SortInterface.js';
import {exchNode} from './Util.js';
export default class Bubble extends SortInterface {

    async sort(list) {
        const length = list.length;
        let iCompareNum = 1,
            iExchangeNum = 1;
        for (let i = 0; i < length - 1; i++) {
            for (let j = 0; j < length - i - 1; j++) {
                $('#step .compare').text(iCompareNum++)
                if (super.less(list[j+1], list[j])) {
                    $('#step .exchange').text(iExchangeNum++)
                    $('#output span').css('background', '#283593');
                    await super.sleep(500);
                    exchNode(j, j+1);
                    list = super.exch(list, j, j+1);

                    await super.sleep(1000);
                } else {     
                    $('#output span').css('background', '#283593');               
                    $('#output span').eq(j).css('background', '#FBC02D');
                    $('#output span').eq(j+1).css('background', '#FBC02D');
                    await super.sleep(1000);
                }
            }
        }
    }
}
