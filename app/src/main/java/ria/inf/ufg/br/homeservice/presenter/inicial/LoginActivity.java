package ria.inf.ufg.br.homeservice.presenter.inicial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ria.inf.ufg.br.homeservice.R;
import ria.inf.ufg.br.homeservice.BaseActivity;
import ria.inf.ufg.br.homeservice.model.FormProblemException;
import ria.inf.ufg.br.homeservice.model.Usuario;

public class LoginActivity extends BaseActivity {

    private final int MIN_PASSWORD = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){

        hideKeyboard();

        try{
            checkEmail();
            checkPassword();
        } catch (FormProblemException e){
            showAlert(e.getMessage());
            return;
        }

        String password = getStringFromEdit(R.id.password);
        String email = getStringFromEdit(R.id.username);

        showDialogWithMessage(getString(R.string.load_login));

        //tryLogin(password,email);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void checkPassword() throws FormProblemException{
        String password = getStringFromEdit(R.id.password);
        if("".equals(password)){
            throw new FormProblemException(getString(R.string.error_password_empty));
        }

        if (password.length() < MIN_PASSWORD){
            throw new FormProblemException(getString(R.string.error_password_small));
        }
    }

    private void checkEmail() throws FormProblemException{
        String email = getStringFromEdit(R.id.username);
        if("".equals(email)){
            throw new FormProblemException(getString(R.string.error_password_empty));
        }
    }

//    private void tryLogin(String password, String email) {
//        WebLogin webLogin = new WebLogin(email,password);
//        webLogin.call();
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Usuario usuario) {
        dismissDialog();
         goToNext();
    }

    private void goToNext() {
        //Intent intent = new Intent(this,CadastroActivity.class);
        //startActivity(intent);
        finish();

    }

    public void cadastrar(View v) {
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
        //finish();

    }
}
