package pt.arnaldocanelas.projetoapi.services;

import java.util.HashSet;
import java.util.Set;

public class JwtBlacklistService {
    private Set<String> blacklistedTokens = new HashSet<>(); // Aqui podemos usar Redis ou banco de dados para persistência

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public void removeTokenFromBlacklist(String token) {
        blacklistedTokens.remove(token);
    }
}
