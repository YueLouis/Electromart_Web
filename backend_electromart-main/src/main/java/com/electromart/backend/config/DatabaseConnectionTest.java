package com.electromart.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConnectionTest {

    @Bean
    CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            System.out.println("\n============================================");
            System.out.println("🔍 TESTING DATABASE CONNECTION...");
            System.out.println("============================================\n");

            try {
                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

                // Test 1: Simple query
                String result = jdbcTemplate.queryForObject("SELECT 'Hello from Railway MySQL!' as message", String.class);
                System.out.println("✅ Connection SUCCESS!");
                System.out.println("✅ Message: " + result);

                // Test 2: Count tables
                Integer tableCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE()",
                    Integer.class
                );
                System.out.println("✅ Database has " + tableCount + " tables");

                // Test 3: List some tables
                System.out.println("\n📋 Available tables:");
                jdbcTemplate.query(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE() LIMIT 10",
                    (rs, rowNum) -> {
                        System.out.println("   - " + rs.getString("table_name"));
                        return null;
                    }
                );

                System.out.println("\n============================================");
                System.out.println("✅ DATABASE CONNECTION IS WORKING!");
                System.out.println("============================================\n");

            } catch (Exception e) {
                System.err.println("\n============================================");
                System.err.println("❌ DATABASE CONNECTION FAILED!");
                System.err.println("============================================");
                System.err.println("Error: " + e.getMessage());
                System.err.println("\n💡 Possible solutions:");
                System.err.println("1. Check Railway MySQL credentials in application.properties");
                System.err.println("2. Verify Railway MySQL service is running");
                System.err.println("3. Check if password has changed in Railway dashboard");
                System.err.println("4. Test connection with MySQL Workbench");
                System.err.println("\n📝 Current config:");
                System.err.println("   URL: " + dataSource.getConnection().getMetaData().getURL());
                System.err.println("   User: " + dataSource.getConnection().getMetaData().getUserName());
                System.err.println("============================================\n");

                // Don't crash the application
                System.err.println("⚠️ Application will continue but API calls will fail");
            }
        };
    }
}
