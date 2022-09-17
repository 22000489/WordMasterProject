package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {

    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        //1 driveway
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        //차고 진입로


        return new Word(0, level, word, meaning);
    }
    public void addItem(){
    Word one = (Word)add();
    list.add(one);
    System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!");
    }


    @Override
    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void selectOne(int id) {
        // TODO Auto-generated method stub

    }
    /*
    => 원하는 메뉴는? 1
    -----------------------------
    1 *** superintendent  관리자, 감독관
    2 *         electric  전기의, 전기를 생산하는
    3 **       equipment  장비, 용품
    */
    public void listAll(){
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){

            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j=0;
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                idlist.add(i);
                j++;
        }
        System.out.println("--------------------------------");
        return idlist;
    }
    public void updateItem() {
        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.print ("\n단어 수정이 성공적으로 되었습니다!!\n");
    }

    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.println("=> 정말로 삭제하실래요?(Y/n) : ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("Y")){
            list.remove((int)idlist.get(id-1));
            System.out.println("\n선택한 단어 삭제 완료!!! ");
        } else
            System.out.println("취소되었습니다. ");
    }
    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;
            while(true) {
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==>" + count + "개 로딩 완료!!!\n");

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt"));
            for(Word one : list){
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("\n모든 단어 파일 저장 완료!!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchlevel() {
        System.out.print("\n=> 레벨(1:초급, 2:중급, 3:고급)선택 : ");
        int level = s.nextInt();
        listAll(level);
    }

    public void listAll(int level){
        int j=0;
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------");
    }

    public void searchWord() {
        System.out.print("=> 검색할 단어 입력 : ");
        String keyword = s.next();
        listAll(keyword);
    }
}
