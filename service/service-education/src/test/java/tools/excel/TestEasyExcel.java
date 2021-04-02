package tools.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {

        //excel 写入
//        String filename = "F:\\dsadas";
//
//        EasyExcel.write(filename,Demodata.class).sheet("学生").doWrite(getdata());

        //read excel
        String filename = "F:\\dsadas";

        EasyExcel.read(filename,Demodata.class, new ExcelListener()).sheet().doRead();

    }

    private static List<Demodata> getdata(){

        ArrayList<Demodata> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Demodata demodata = new Demodata();
            demodata.setSno(i);
            demodata.setSname("lucy" + i);
            list.add(demodata);
        }
        return list;
    }




}
