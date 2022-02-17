package com.cydeo.tests.practice;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadDataFromExcelFile {
    public static void main(String[] args) throws IOException {


        String filePath="src/test/resources/data.xlsx";
        String sheetName="data";

        InputStream in =new FileInputStream(filePath);
        Workbook workbook= WorkbookFactory.create(in);

        Sheet sheet =workbook.getSheet(sheetName);

        Row firstRow =sheet.getRow(1);
        Cell firstCell =firstRow.getCell(3);
        System.out.println("firstCell.getStringCellValue() = " + firstCell.getStringCellValue());
        System.out.println("firstRow.getPhysicalNumberOfCells() = " + firstRow.getPhysicalNumberOfCells());

        firstRow.cellIterator().forEachRemaining(System.out::println);
        System.out.println("sheet.getPhysicalNumberOfRows() = " + sheet.getPhysicalNumberOfRows());


        sheet.forEach( eachRow-> {
            eachRow.cellIterator().forEachRemaining(eachCell-> System.out.print(eachCell.toString()+" "));
            System.out.println("");
        });

    }

}
