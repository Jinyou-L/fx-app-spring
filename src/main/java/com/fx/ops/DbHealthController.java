package com.fx.ops;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class DbHealthController {
    private final JdbcTemplate jdbc;
    public DbHealthController(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    @GetMapping("/api/health/db")
    public Map<String, Integer> health() { return Map.of("currency", count("currency"), "account", count("account"), "fx_rate", count("fx_rate"), "transfer", count("transfer")); }
    private int count(String table) { return jdbc.queryForObject("SELECT COUNT(*) FROM " + table, Integer.class); }
}
