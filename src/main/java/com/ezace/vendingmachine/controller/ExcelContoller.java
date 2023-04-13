package com.ezace.vendingmachine.controller;



import com.ezace.vendingmachine.service.ExcelService;
import com.ezace.vendingmachine.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExcelContoller {

    private final ExcelService excelService;
    private final GoodsService goodsService;
    @GetMapping("/excel/download")
    public void excelDownLoad(HttpServletResponse response)   {
        excelService.excelDownloadByAllSales(response);
    }

    @PostMapping("/excel/upload")
    public String  excelUpload(@RequestParam("file") MultipartFile file, Model model) {
       excelService.excelUpload(file, model);
        if (model.getAttribute("error") != null) {
            model.addAttribute("error", "액셀 파일만 가능합니다.");
            return "redirect:/sales-manage";
        }

        model.addAttribute("salesList", goodsService.findAllGoods());
        return "redirect:/sales/manage";
    }
}
