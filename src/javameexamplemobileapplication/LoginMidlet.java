package javameexamplemobileapplication;

import java.util.Date;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * Um exemplo de login usando J2ME (Java ME).
 *
 * @author Vladwoguer Bezerra
 */
public class LoginMidlet extends MIDlet implements CommandListener {

    private static final String TITLE_FORMULARIO_PRINCIPAL = "Faça login";
    private static final String SENHA_VALOR_INICIAL = "";
    private static final String USUARIO_VALOR_INICIAL = "";
    private static final String LABEL_SENHA = "Senha:";
    private static final String LABEL_USUARIO = "Usuario:";
    private static final String BOTAO_SAIR = "Sair";
    private static final String BOTAO_ENTRAR = "Entrar";
    private static final String LABEL_DIA_DE_HOJE = "";
    private static final String MENSAGEM_BOAS_VINDAS = "Welcome";
    private static final String LABEL_BOAS_VINDAS = "";
    private static final String TITLE_FORMULARIO_BOAS_VINDAS = "Logged";
    private static final String VERIFIQUE_USUÁRIO_E_SENHA = "Verifique usuário e senha";
    private static final String TITLE_ALERT_FALHOU_LOGIN = "Login falhou";

    private final Display tela;
    private final Form formularioLogin;
    private Form formularioBoasVindas;
    private final Command botaoSubmeter;
    private final Command sair;
    private Command sairBoasVindas;
    private final TextField senha;
    private TextField bemVindo;
    private final TextField usuario;
    private Alert alert;
    private Date diaDeHoje;
    private DateField campoData;

    private final LoginMidlet thisIntance = this;

    public LoginMidlet() {

        tela = Display.getDisplay(thisIntance);
        botaoSubmeter = new Command(BOTAO_ENTRAR, Command.SCREEN, 1);
        sair = new Command(BOTAO_SAIR, Command.EXIT, 1);
        usuario = new TextField(LABEL_USUARIO, USUARIO_VALOR_INICIAL, 30, TextField.ANY);
        senha = new TextField(LABEL_SENHA, SENHA_VALOR_INICIAL, 30, TextField.ANY | TextField.PASSWORD);
        formularioLogin = new Form(TITLE_FORMULARIO_PRINCIPAL);
        formularioLogin.addCommand(sair);
        formularioLogin.addCommand(botaoSubmeter);
        formularioLogin.append(usuario);
        formularioLogin.append(senha);
        formularioLogin.setCommandListener(thisIntance);
    }

    public void startApp() {
        tela.setCurrent(formularioLogin);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command command, Displayable displayable) {

        if (command == botaoSubmeter) {

            if (verificarUsuario(usuario.getString(), senha.getString())) {
                depoisDoLogin();
            } else {
                alert = new Alert(TITLE_ALERT_FALHOU_LOGIN, VERIFIQUE_USUÁRIO_E_SENHA, null, null);
                alert.setTimeout(Alert.FOREVER);
                tela.setCurrent(alert, formularioLogin);
            }

        } else if (command == sair || command == sairBoasVindas) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    private boolean verificarUsuario(String usuario, String senha) {
        Usuario mock = new Usuario("ADMIN", "PASS");

        return mock.getUsuario().toUpperCase().equals(usuario.toUpperCase()) && mock.getSenha().toUpperCase().equals(senha.toUpperCase());
    }

    private void depoisDoLogin() {
        formularioBoasVindas = new Form(TITLE_FORMULARIO_BOAS_VINDAS);

        bemVindo = new TextField(LABEL_BOAS_VINDAS, MENSAGEM_BOAS_VINDAS, 30, TextField.ANY);
        diaDeHoje = new Date(System.currentTimeMillis());
        campoData = new DateField(LABEL_DIA_DE_HOJE, DateField.DATE_TIME);
        campoData.setDate(diaDeHoje);

        formularioBoasVindas.append(bemVindo);
        formularioBoasVindas.append(campoData);

        sairBoasVindas = new Command(BOTAO_SAIR, Command.EXIT, 1);
        formularioBoasVindas.addCommand(sairBoasVindas);
        formularioBoasVindas.setCommandListener(thisIntance);

        tela.setCurrent(formularioBoasVindas);
    }
}
