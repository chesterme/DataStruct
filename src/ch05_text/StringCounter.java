package ch05_text;

import java.io.*;

/**
 * 给定一个英文文本文件，统计文件中所有单词出现的频率，并输出词频最大的前10%的单词
 * 及其词频，假设单词字符定义为大小写字母、数字和下划线，其他字符均认为是单词的分隔符
 */
public class StringCounter {

    private InputStreamReader reader;
    private CounterNode<String>[] headers;
    private MyHash myHash;
    private int tableSize;
    private static final int MAX_WORD_LENGTH = 80;  // 所有单词的最大长度为80

    public StringCounter(int tableSize, MyHash myHash, String fileName) {
        this.tableSize = nextPrime(tableSize);
        this.myHash = myHash;
        try{
            reader = new FileReader(new File(fileName));
        }catch(FileNotFoundException e){
            System.out.println("无法打开文件：" + fileName);
            e.printStackTrace();
        }
        create();
    }

    // 创建一个空白的散列表
    private void create(){
        headers = new CounterNode[tableSize];
        for(int i = 0; i < headers.length; i++){
            headers[i] = new CounterNode<>(null,0, null);
        }
    }

    // 返回一个大于size的最小素数
    private int nextPrime(int size){
        int i, p = size % 2 == 0 ? size + 1 : size + 2;
        while(true){
            for(i = (int)Math.sqrt(p); i > 2; i--){
                if(p % i == 0){
                    // 说明p不是素数
                    break;
                }
            }
            if(i == 2){
                // 说明p是素数
                break;
            }else{
                // 查找下一个奇数
                p += 2;
            }
        }
        return p;
    }

    // 从散列表中查找元素x，若找到则返回该节点，否则返回null
    public CounterNode<String> find(String x){
        CounterNode<String> tempNode;
        int hashIndex = myHash.hash(x, tableSize);
        // 获取hash值对应链表的第一个节点元素
        tempNode = headers[hashIndex].getNext();
        // 遍历该链表
        while(tempNode != null){
            if(!tempNode.getData().equals(x)){
                tempNode = tempNode.getNext();
            }
            else{
                break;
            }
        }
        return tempNode;
    }

    // 判断散列表中是否存在元素x
    public boolean isExist(String x){
        return find(x) == null ? false : true;
    }

    // 在散列表中插入一个元素x
    public void insert(String x){
        // 获取hash值
        int insertIndex = myHash.hash(x, tableSize);
        CounterNode<String> header = headers[insertIndex];
        // 如果散列表中不存在该元素，则插入
        if(!isExist(x)){
            // 新链表元素插入在表头
            CounterNode<String> tempNode = new CounterNode<>(x,1, null);
            tempNode.setNext(header.getNext());
            header.setNext(tempNode);
            // 统计链表的长度
            header.setCounter(header.getCounter() + 1);
        }
        // 若存在则将该元素的计数器 + 1
        else{
            header = header.getNext();
            while(header != null){
                if(header.getData().equals(x)){
                    header.setCounter(header.getCounter() + 1);
                    break;
                }
                header = header.getNext();
            }
        }
    }

    // 在散列表中删除元素x
    public CounterNode<String> delete(String x){
        if(isExist(x)){
            int deleteIndex = myHash.hash(x, tableSize);
            CounterNode<String> previousNode = headers[deleteIndex];
            CounterNode<String> currentNode = headers[deleteIndex].getNext();
            while(!currentNode.getData().equals(x)){
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            previousNode.setNext(currentNode.getNext());
            return currentNode;
        }
        else{
            System.out.println("散列表不存在该元素，无法删除");
            return null;
        }
    }

    // 打印散列表
    public void printAll(){
        for(int i = 0; i < tableSize; i++){
            if(headers[i].getNext() != null){
                System.out.println("下标为：" + i + " 的链表元素为：");
                CounterNode<String> tempNode = headers[i].getNext();
                while(tempNode != null){
                    System.out.println(tempNode);
                    tempNode = tempNode.getNext();
                }
            }
        }
    }

    // 判断是否是合法的单词字符
    public boolean isWordChar(char c){
//        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c == '_'){
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'){
            return true;
        }
        return false;
    }

    // 从输入流中读取一个单词
    public String getAWord(char c){
        char[] tempWord = new char[MAX_WORD_LENGTH + 1];
        int tempChar; // 保存从流中读取的一个字符
        int length = 0; // 单词的实际长度
        tempWord[length++] = c;
        try{
            // 未到文件结尾
            while((tempChar = reader.read()) != -1){
                // 如果是合法的单词字符
                if(isWordChar((char)tempChar)){
                    tempWord[length++] = (char)tempChar;
                }
                // 遇到不是合法的单词字符，将它们当作是单词之间的分隔符，此时一个单词读取完毕
                if(length > 0 && !isWordChar((char)tempChar)){
                    break;
                }
            }
            tempWord[length] = '\0'; // 设置字符串结束符
            // 如果单词太长，则截取所允许的最大长度
            if(length > MAX_WORD_LENGTH){
                tempWord[MAX_WORD_LENGTH] = '\0';
                length = MAX_WORD_LENGTH;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        String result = new String(tempWord, 0, length);
        return result;
    }

    /**
     * 显示词频前一定百分比的单词
     * @param percent，百分比
     */
    public void show(double percent){
        int diffWordCount = 0; // 不同单词数量
        int maxf = 0; // 最大的词频
        int maxCollision = 0; //最大冲突次数
        int minCollision = 100; // 最小冲突次数
        CounterNode<String> tempNode;

        // 扫描整个散列表，统计不同的单词数量和最大词频
        for(int i = 0; i < tableSize; i++){
            // 求不同单词的数量
            diffWordCount += headers[i].getCounter(); // 链表头节点的counter统计量表的长度
            // 统计最大和最小冲突次数
            if(maxCollision < headers[i].getCounter()){
                maxCollision = headers[i].getCounter();
            }
            if(minCollision > headers[i].getCounter()){
                minCollision = headers[i].getCounter();
            }
            // 求最大词频
            tempNode = headers[i].getNext(); // 链表的第一个元素
            while(tempNode != null){
                if(maxf < tempNode.getCounter()){
                    maxf = tempNode.getCounter();
                }
                tempNode = tempNode.getNext();
            }
        }

        System.out.printf("共有 %d 个不同的单词，词频最大是 %d;\n", diffWordCount, maxf);
        System.out.printf("最大冲突次数是：%d.\n 最小冲突次数是：%d.\n", maxCollision, minCollision);

        int[] diffWords = new int[maxf + 1]; // 每个词频对应的不同单词数量
        // 求每个词频等级拥有的不同单词数量
        for(int i = 0; i < diffWords.length; i++){
            diffWords[i] = 0;
        }
        // 遍历整个散列表，统计每个词频的出现次数
        for(int i = 0; i < headers.length; i++){
            tempNode = headers[i].getNext();
            while(tempNode != null){
                diffWords[tempNode.getCounter()]++;
                tempNode = tempNode.getNext();
            }
        }

        // 获取显示范围
        int bound = (int)(diffWordCount * percent);
        int count = 0; // 统计单词
        int indexBound;
        for(indexBound = maxf; indexBound >= 1 && count < bound; indexBound--){
            count += diffWords[indexBound];
        }

        System.out.printf("词频最大的前 %d %% 的单词和词频分别为：\n", (int)(percent * 100));
        int sum = 0;
        // 按词频从大到小输出单词
        for(int i = maxf; i >= indexBound; i--){
            for(int j = 0; j < headers.length; j++){
                tempNode = headers[j].getNext();
                while(tempNode != null){
                    if(tempNode.getCounter() == i){
                        System.out.printf("%-15s: %d\n", tempNode.getData(), tempNode.getCounter());
                        sum ++;
                    }
                    tempNode = tempNode.getNext();
                }
            }
        }

        System.out.printf("一共 %d 个单词", sum);
    }

    public void main(double percent){
        int tempChar;
        int wordCounter = 0; // 有效单词
        try{
            while((tempChar = reader.read()) != -1){
                if(isWordChar((char)tempChar)){
                    String word = getAWord((char)tempChar);
                    if(word.length() > 3){
                        wordCounter++;
                        insert(word);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.printf("该文档中共出现 %d 个有效单词\n", wordCounter);
        show(percent);
    }
}
