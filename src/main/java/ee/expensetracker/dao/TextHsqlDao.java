package ee.expensetracker.dao;

import ee.expensetracker.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

//@Primary
//@Repository
public class TextHsqlDao implements Dao {

    @Autowired
    private JdbcTemplate template;

    public Text save(Text text) {
        //find the category by name. insert too
        String sql = "insert into expense (id, text, amount) "
                + "values (NEXT VALUE FOR seq1, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        template.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(
                    sql, new String[] {"id"});

            System.out.println(ps);

            ps.setString(1, text.getText());
            ps.setDouble(2, text.getAmount());

            return ps;

        }, holder);

        text.setId(holder.getKey().longValue());

        return text;
    }

    public List<Text> findAll() {
        String sql = "select * from text_table";

        return template.query(sql, (rs, rowNum) -> new Text(
                rs.getLong("id"),
                rs.getString("text"),
                rs.getDouble("amount")));
    }

}
