export default class SortInterface {
    sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    less(v, w) {        
        return v - w < 0;
    }

    exch(list, i, j) {
        const tmp = list[j];
        list[j] = list[i];
        list[i] = tmp;

        return list;
    }

}
