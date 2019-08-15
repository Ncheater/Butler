import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Strings {
	private static String[] boasVindas = {
  "**Seja bem vindo(a) %user% ao servidor %guild%. Cola com nós que é sucesso!**",
  "**Bem vindo(a) %user% ao servidor %guild%.**",
  "**Bem vindo(a) %user% passa em <#610181940624228385> para ganha novas tag's**",
  "**CHi, alá! Os gados chegando no servidor. Seja bem vindo(a) %user% **",
  "**Quer nuds quer? %user% ? Divulga o servidor que você ganha.**",
  "**E ae viaddo, quanto tempo. Seja bem vindo(a) e nos conte as novidades.**",
  "**Salve %user%, passa no <#610181940624228385> pra ganhar carginho.**"
  
};r %guild%. Cola com nós que é sucesso!**",
    "**Bem vindo(a) %user% passa em <#610181940624228385> para ganha novas tag's**"
};

	private static String[] adeus = {

	};

	static String getMsg(MessageType type) {
		List<String> strings;
		if (type == MessageType.BOASVINDAS) {
			strings = Arrays.asList(boasVindas);
			int index = new Random().nextInt(strings.size());
			return strings.get(index > strings.size() - 1 ? strings.size() - 1 : index);
		} else if (type == MessageType.ADEUS){
			strings = Arrays.asList(adeus);
			int index = new Random().nextInt(strings.size());
			return strings.get(index > strings.size() - 1 ? strings.size() - 1 : index);
		} else {
			return null;
		}
	}
}
