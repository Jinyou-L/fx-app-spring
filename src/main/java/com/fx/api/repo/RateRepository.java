package com.fx.api.repo;

import com.fx.api.model.Rate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class RateRepository {
    private final JdbcTemplate jdbc;
    private static final RowMapper<Rate> MAPPER = (rs, n) -> new Rate(
            rs.getInt("id"), rs.getString("base_code"), rs.getString("quote_code"),
            rs.getDouble("rate"), rs.getDate("rate_date").toLocalDate());

    public RateRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
    public List<Rate> findLatest() {
        return jdbc.query("SELECT r.* FROM fx_rate r WHERE r.rate_date = (SELECT MAX(rate_date) FROM fx_rate) ORDER BY r.base_code, r.quote_code", MAPPER);
    }
    public Optional<Rate> findLatestForPair(String base, String quote) {
        return jdbc.query("SELECT r.* FROM fx_rate r WHERE r.base_code = ? AND r.quote_code = ? ORDER BY r.rate_date DESC LIMIT 1", MAPPER, base, quote).stream().findFirst();
    }
}
