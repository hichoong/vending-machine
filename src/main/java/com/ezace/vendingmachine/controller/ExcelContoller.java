package com.ezace.vendingmachine.controller;



import com.ezace.vendingmachine.domain.vo.ExcelData;
import com.ezace.vendingmachine.domain.vo.GoodsVo;
import com.ezace.vendingmachine.service.ExcelService;
import com.ezace.vendingmachine.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExcelContoller {

    private final ExcelService excelService;
    private final GoodsService goodsService;
    @PostMapping("/excel/read")
    public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        List<ExcelData> dataList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            ExcelData data = new ExcelData();
            data.setId((long) row.getCell(0).getNumericCellValue());
            data.setName(row.getCell(1).getStringCellValue());
            data.setEmail(row.getCell(2).getStringCellValue());
            dataList.add(data);
        }
        model.addAttribute("datas", dataList); // 5
        return "excelList";
    }

    @GetMapping("/excel/download")
    public void excelDownLoad(HttpServletResponse response)   {
        excelService.excelDownloadByAllSales(response);
    }

    @PostMapping("/excel/upload")
    public String  excelUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            /*ExcelData data = new ExcelData();
            data.setId((long) row.getCell(0).getNumericCellValue());
            data.setName(row.getCell(1).getStringCellValue());
            data.setEmail(row.getCell(2).getStringCellValue());
            dataList.add(data);*/
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setId((long)row.getCell(0).getNumericCellValue());
            goodsVo.setName(row.getCell(1).getStringCellValue());
            goodsVo.setPrice((int)row.getCell(2).getNumericCellValue());
            goodsVo.setCount((int)row.getCell(3).getNumericCellValue());
            goodsVo.setImage(row.getCell(4).getStringCellValue());
            goodsService.insertGoods(goodsVo);
        }
        workbook.close();
        return "redirect:/sales";
    }
}
