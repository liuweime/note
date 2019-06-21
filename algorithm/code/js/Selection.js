import SortInterface from './SortInterface.js';
import {exchNode} from './Util.js';
export default class Selection extends SortInterface {

    async sort(list) {
        const length = list.length;

        for (let i = 0; i < length; i++) {
            let min = i;
            for (let j = i+1; j < length; j++) {
                if (list[min] > list[j]) {
                    min = j;
                }
            }
            let tmp = list[min];
            list[min] = list[i];
            list[i] = tmp;

            exchNode(min, i);
            await this.sleep(1000);
        }
    }

}
