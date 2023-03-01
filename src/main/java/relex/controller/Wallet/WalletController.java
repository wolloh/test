package relex.controller.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import relex.controller.Wallet.Models.*;
import relex.service.Models.CurrencyRateModel;
import relex.service.Models.ExchangeModel;
import relex.service.Models.WalletModel;
import relex.service.WalletService;
import relex.utils.MapperWallets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(path="/api")
public class WalletController {

    private final WalletService _walletService;
    private final MapperWallets _mapper;

    @Autowired
    public WalletController(WalletService walletService, MapperWallets mapper) {
        this._mapper=mapper;
        this._walletService=walletService;
    }


    @RequestMapping(
            path = "/get_balance",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<ArrayList<WalletResponse>> getBalance(@RequestParam("key") String key) {
        ArrayList<WalletModel> walletsBalance=_walletService.getWallets(key);
        return new ResponseEntity<>(_mapper.mapWallets(walletsBalance),HttpStatus.OK);
    }


    @RequestMapping(
            path = "/deposit",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<WalletResponse> depositMoney(@RequestBody DepositRequest depositRequest)  {
        WalletModel wallet=_walletService.updateMoney(depositRequest);

        return new ResponseEntity<>(_mapper.mapWallet(wallet), HttpStatus.OK);
    }

    @RequestMapping(
            path = "/withdraw",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<WalletResponse> withDrawMoney(@RequestBody WithdrawalRequest withdrawalRequest)  {
        WalletModel wallet=_walletService.withDraw(withdrawalRequest);

        return new ResponseEntity<>(_mapper.mapWallet(wallet),HttpStatus.OK);
    }


    @RequestMapping(
            path = "/get_rate",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<ArrayList<CurrencyRateModel>> getRates(@RequestParam("key") String key,
                                                                 @RequestParam("currency") String currency)  {
        ArrayList<CurrencyRateModel> rates=_walletService.getRate(key,currency);
        return new ResponseEntity<>(rates,HttpStatus.OK);
    }

    @RequestMapping(
            path = "/exchange",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<ExchangeModel> exchangeMoney(@RequestBody ExchangeRequest exchangeRequest)   {
        ExchangeModel exchange=_walletService.exchangeMoney(exchangeRequest);
        return new ResponseEntity<>(exchange,HttpStatus.OK);
    }


    @RequestMapping(
            path = "/get_sum",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<GetSumResponse> getSumCurrency(@RequestParam("key") String key,@RequestParam("currency") String currency) {
        BigDecimal sum=_walletService.getSumCurrency(key,currency);
        return new ResponseEntity<>(new GetSumResponse(currency,sum),HttpStatus.OK);
    }

    @RequestMapping(
            path = "/get_count_transactions",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<GetTransactionsCountResponse> getCountTransactions(@RequestParam("key") String key,
                                                             @RequestParam("dateFrom") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
                                                       @RequestParam("dateTo") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo  ) {
        Integer count=_walletService.countTransaction(key,dateFrom,dateTo);
        return new ResponseEntity<>(new GetTransactionsCountResponse(count),HttpStatus.OK);
    }
}

