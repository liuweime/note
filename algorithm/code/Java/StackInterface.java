public interface StackInterface<T> {

    /**
     * 入栈
     *
     * @param item 入栈元素
     */
    public void push(T item);


    /**
     * 出栈
     *
     * @return 出栈元素
     */
    public T pop();
}
