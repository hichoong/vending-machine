package com.ezace.vendingmachine.service;

import com.ezace.vendingmachine.domain.dto.response.SalesResponse;
import com.ezace.vendingmachine.domain.vo.GoodsVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelService {
    private final SalesService salesService;
    private final GoodsService goodsService;
    public void excelDownloadByAllSales(HttpServletResponse response) {
        List<SalesResponse> allSales = salesService.findAllSales();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        // Header
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        cell.setCellValue("id");
        cell = row.createCell(1);
        cell.setCellValue("상품");
        cell = row.createCell(2);
        cell.setCellValue("판매수");
        cell = row.createCell(3);
        cell.setCellValue("판매가격");
        cell = row.createCell(4);
        cell.setCellValue("판매일자");
        // Body
        for (SalesResponse sales : allSales) {
            row = sheet.createRow(++rowNum);
            cell = row.createCell(0);
            cell.setCellValue(sales.getId());
            cell = row.createCell(1);
            cell.setCellValue(sales.getName());
            cell = row.createCell(2);
            cell.setCellValue(sales.getCount());
            cell = row.createCell(3);
            cell.setCellValue(sales.getPrice());
            //날짜 타입의 경우 포맷이 필요
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            cell = row.createCell(4);
            cell.setCellValue(sales.getSalesDate());
            cell.setCellStyle(cellStyle);
        }
        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment; filename=total.xlsx");
        try {
            // 엑셀 파일을 다운로드 하기 위해 write() 메서드를 사용한다.
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            // checked 예외를 사용하면 추후 의존이나 예외 누수 문제가 생길 수 있으므로
            // RuntimeException으로 한번 감싸서, cause가 나올 수 있게 발생한 예외를 넣어준다.
            log.error("Workbook  수행 중 IOException 발생!");
            throw new RuntimeException(e);
        }finally {
            workbookClose(workbook);
        }
    }
    private void workbookClose(Workbook workbook) {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Transactional
    public Model excelUpload(MultipartFile file, Model model) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            model.addAttribute("error", "액셀 파일만 넣어주세요.");
            return model;
        }
        Workbook workbook = null;
        try {
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        Sheet worksheet = workbook.getSheetAt(0);
        //DB 데이터 품목 삭제
        goodsService.deleteGoods();

        //DB에 새로 넣을 데이터 구성
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setId((long)row.getCell(0).getNumericCellValue());
            goodsVo.setName(row.getCell(1).getStringCellValue());
            goodsVo.setPrice((int)row.getCell(2).getNumericCellValue());
            goodsVo.setCount((int)row.getCell(3).getNumericCellValue());
            //재고가 30을 넘길 시 30으로 조정
            if (goodsVo.getCount() > 30) {
                goodsVo.setCount(30);
            }
            goodsVo.setImage(row.getCell(4).getStringCellValue());
            goodsService.insertGoods(goodsVo);
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
         finally {
            workbookClose(workbook);
        }
        model.addAttribute("success", "업로드 성공");
        return model;
    }
}
