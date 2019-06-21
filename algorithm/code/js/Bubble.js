import SortInterface from './SortInterface.js';
import {exchNode} from './Util.js';
export default class Bubble extends SortInterface {

    async sort(list) {
        const length = list.length;
        for (let i = 0; i < length - 1; i++) {
            for (let j = 0; j < length - i - 1; j++) {
                if (super.less(list[j+1], list[j])) {
                    exchNode(j, j+1);
                    list = super.exch(list, j, j+1);

                    await super.sleep(1000);
                }
            }
        }
    }
}
