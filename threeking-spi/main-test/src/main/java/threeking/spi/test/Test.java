package threeking.spi.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {




    public static void main(String[] args) {

        PersonOne aa = new PersonOne(1,"张三",20);
        PersonOne ab = new PersonOne(2,"李四",21);
        PersonOne ac = new PersonOne(3,"王五",22);

        List<PersonOne> listA = new ArrayList();
        listA.add(aa);
        listA.add(ab);
        listA.add(ac);

        PersonTwo ba = new PersonTwo(1,"张三",1);
        PersonTwo bb = new PersonTwo(2,"李四",2);
        PersonTwo bc = new PersonTwo(3,"王五",1);

        List<PersonTwo> listB = new ArrayList();
        listB.add(ba);
        listB.add(bb);
        listB.add(bc);

        Stream<?> objectStream = Stream.of(listA, listB).flatMap(Test::merge);

        objectStream.collect(Collectors.toList()).forEach(System.out::println);

    }

    private static Stream<?> merge(List<? extends PersonBase> it) {
        List<PersonThree> list = new ArrayList<>();
        for (PersonBase i : it) {
            list.add((PersonThree) i);
        }
        return list.stream();
    }



    static class PersonBase {
        private int id;
        private String name;
        public PersonBase(int id , String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    static class PersonOne extends PersonBase {
        private int age;

        public PersonOne(int id , String name, int age) {
            super(id,name);
            this.age= age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    static class PersonTwo extends PersonBase {
        private int sex;
        public PersonTwo(int id , String name, int sex) {
            super(id,name);
            this.sex = sex;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }




    static class PersonThree extends PersonBase {

        private int age;
        private int sex;
        public PersonThree(int id , String name,int age, int sex) {
            super(id,name);;
            this.age = age;
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
