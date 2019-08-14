import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Objects;

public class Main extends ListenerAdapter {

	private static JDA api;
	private static TextChannel channel;

	public static void main(String[] args) {
		try {
			api = new JDABuilder(AccountType.CLIENT)
					.setToken(System.getenv("TOKEN"))
					.setAutoReconnect(true)
					.build();
		} catch (LoginException e) {
			System.out.println("Erro ao conectar: " + e + " -> " + e.getStackTrace()[0]);
		}
		finishSetup();
	}

	private static void finishSetup() {
		api.getPresence().setStatus(OnlineStatus.ONLINE);
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("//set")) return;
		String[] args = event.getMessage().getContentRaw().split(" ");

		if ("canal".equals(Arrays.copyOfRange(args, 1, args.length - 1)[0].trim())) {
			channel = event.getChannel();
			channel.sendMessage("Ta bom cara, vou falar nesse canal quando alguem entrar.").queue();
		} else {
			event.getChannel().sendMessage("Conheço esse comando não.").queue();
		}
	}

	@Override
	public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
		try {
			channel.sendMessage(Objects.requireNonNull(Strings.getMsg(MessageType.BOASVINDAS)).replace("%user%", event.getUser().getAsMention()).replace("%guild%", event.getGuild().getName())).queue();
		} catch (Exception e) {
			api.getUserById("411216909422559242").openPrivateChannel().queue(c -> c.sendMessage("Erro ao encontrar o canal de mensagens:```" + e + " -> " + e.getStackTrace()[0] + "```").queue());
		}
	}

	@Override
	public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event) {
		try {
			channel.sendMessage(Objects.requireNonNull(Strings.getMsg(MessageType.ADEUS)).replace("%user%", event.getUser().getAsMention()).replace("%guild%", event.getGuild().getName())).queue();
		} catch (Exception e) {
			api.getUserById("411216909422559242").openPrivateChannel().queue(c -> c.sendMessage("Erro ao encontrar o canal de mensagens:```" + e + " -> " + e.getStackTrace()[0] + "```").queue());
		}
	}
}
