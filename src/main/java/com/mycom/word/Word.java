package com.mycom.word;

public class Word {

    private int id;
    private int level;
    private String word;
    private String meaning;

    Word(){}
    Word(int id, int level, String word, String meaning){
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    @Override
    public String toString(){
        // 각각의 data는 자신의 순서를 알 수 없으므로 object를 이용한 것이 아닌 list를 이용해 순서 data를
        // 가져올 예정
        // 1 *** superintendent  관리자, 감독관
        // 2 *         electric  전기의, 전기를 생산하는
        // 3 **       equipment  장비, 용품
        String slevel = "";
        for(int i=0; i<level; i++) slevel += "*";
        String str = String.format("%-3s", slevel)
                + String.format("%15s", word)
                + "  " + meaning;
        // format이라는 함수 이용해 정렬하는데, 왼 쪽 정렬 해야하므로 -3으로 서식문자 설정해줌
        // 단어는 오른쪽에 약 15칸 정도를 공백으로 두어 정렬해줌
        return str;
    }
    public String toFileString() {
        return this.level + "|" + this.word + "|" + this.meaning;
    }

}