package com.study.web.command;

import java.util.HashMap;
import java.util.Map;

import static com.study.web.constant.PathConstants.*;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new PageNotFound();


    static {
        getCommandMap.put(SLASH_INDEX, new ConferencesCommand());
        getCommandMap.put(SLASH_FORBIDDEN, new ForbiddenCommand());
        getCommandMap.put(SLASH_LOGOUT, new LogOutCommand());
        getCommandMap.put(SLASH_LOGIN, new LogInCommand());
        getCommandMap.put(SLASH, new ConferencesCommand());
        getCommandMap.put(SLASH_SPEAKERS, new SpeakersCommand());
        getCommandMap.put(SLASH_ADMINISTRATION, new AdministrationCommand());
        getCommandMap.put(SLASH_ABOUT, new AboutCommand());
        getCommandMap.put(SLASH_REGISTRATION, new RegistrationCommand());
        postCommandMap.put(SLASH_LOGIN, new PostLogInCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path, String type) {
        return "GET".equals(type)
                ? getCommand(path)
                : postCommand(path);
    }

    private static Command getCommand(String path) {
        return getCommandMap.getOrDefault(path, defaultCommand);
    }

    private static Command postCommand(String path) {
        return postCommandMap.getOrDefault(path, defaultCommand);
    }
}
