//package com.dggl.amei.services;
//
//import com.dggl.amei.models.Orcamento;
//import com.dggl.amei.repositories.OrcamentoRepository;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVPrinter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.io.Writer;
//import java.time.LocalDateTime;
//import java.util.LinkedList;
//import java.util.List;
//
//@Service
//public class CsvExportService {
//
//    @Autowired
//    private OrcamentoRepository orcamentoRepository;
//
//    public void exportaOrcamentoParaCsvPorPeriodo(Writer writer, LocalDateTime dataInicio, LocalDateTime dataFim){
//
//        List<Orcamento> orcamentos = orcamentoRepository.findByDataBetween(dataInicio, dataFim);
//
//        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
//            csvPrinter.printRecord("Cliente", "Status Orçamento", "Data Emissão", "Valor Total");
//            for(Orcamento orcamento : orcamentos){
//                csvPrinter.printRecord(
//                       orcamento.getNomeCliente(),
//                       orcamento.getStatus(),
//                       orcamento.getDataEmissaoOrcamento(),
//                       orcamento.getValorTotalDoOrcamento()
//                );
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void exportaOrcamentoParaCsv(Writer writer){
//
//        List<Orcamento> orcamentos = orcamentoRepository.findAll();
//        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
//            csvPrinter.printRecord("Cliente", "Status Orçamento", "Data Emissão", "Valor Total");
//            for(Orcamento orcamento : orcamentos){
//                csvPrinter.printRecord(
//                        orcamento.getNomeCliente(),
//                        orcamento.getStatus(),
//                        orcamento.getDataEmissaoOrcamento(),
//                        orcamento.getValorTotalDoOrcamento()
//                );
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
