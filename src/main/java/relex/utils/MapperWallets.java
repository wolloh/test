package relex.utils;

import org.springframework.stereotype.Component;
import relex.controller.Wallet.Models.BTC_wallet;
import relex.controller.Wallet.Models.RUB_wallet;
import relex.controller.Wallet.Models.TON_wallet;
import relex.controller.Wallet.Models.WalletResponse;
import relex.service.Models.WalletModel;

import java.util.ArrayList;
@Component
public final class MapperWallets {
    public static ArrayList<WalletResponse> mapWallets(ArrayList<WalletModel> walletModels){
        ArrayList<WalletResponse> response=new ArrayList<WalletResponse>();
        for (WalletModel walletModel:walletModels){
            if(walletModel.getWalletType().equals("RUB_wallet")){
                RUB_wallet rub_Wallet=new RUB_wallet(walletModel.getBalance());
                response.add(rub_Wallet);
            }
            if(walletModel.getWalletType().equals("TON_wallet")){
                TON_wallet ton_Wallet=new TON_wallet(walletModel.getBalance());
                response.add(ton_Wallet);
            }
            if(walletModel.getWalletType().equals("BTC_wallet")){
                BTC_wallet btc_Wallet=new BTC_wallet(walletModel.getBalance());
                response.add(btc_Wallet);
            }
        }
        return response;
    }
    public static WalletResponse mapWallet(WalletModel walletModel){
        WalletResponse response=new WalletResponse(){};
            if(walletModel.getWalletType().equals("RUB_wallet")){
                response=new RUB_wallet(walletModel.getBalance());
            }
            if(walletModel.getWalletType().equals("TON_wallet")){
                response=new TON_wallet(walletModel.getBalance());

            }
            if(walletModel.getWalletType().equals("BTC_wallet")){
                response=new BTC_wallet(walletModel.getBalance());
            }
        return response;
    }

}
