package pl.projekt.simplecantor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.projekt.simplecantor.database.entity.ExchangeHistory;
import pl.projekt.simplecantor.dto.CurrencyCurrentRate;
import pl.projekt.simplecantor.dto.ExchangeHistoryDto;
import pl.projekt.simplecantor.dto.ExchangeRequestDto;
import pl.projekt.simplecantor.dto.ExchangeResultDto;
import pl.projekt.simplecantor.service.client.ClientService;

import java.util.List;


@RestController
@RequestMapping("exchange")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
class ClientController {

    private final ClientService clientService;

    @GetMapping("rate")
    public List<CurrencyCurrentRate> getAllCurrency() { return clientService.showAllCurrency(); }

    @PostMapping("{currencyCode}")
    @ResponseBody
    public ExchangeResultDto exchange(@PathVariable String currencyCode,
                                      @RequestBody ExchangeRequestDto dto) {
        return clientService.doExchange(currencyCode, dto);
    }

    @GetMapping("history")
    @ResponseBody
    public List<ExchangeHistoryDto> getHistory() {
        return clientService.getHistory();
    }

}

