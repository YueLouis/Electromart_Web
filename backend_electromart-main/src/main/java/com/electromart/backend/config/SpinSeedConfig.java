package com.electromart.backend.config;

import com.electromart.backend.model.SpinReward;
import com.electromart.backend.model.SpinRewardType;
import com.electromart.backend.repository.SpinRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SpinSeedConfig {

    // Commented out to prevent startup crash if DB connection fails
    // Uncomment this if you want to auto-seed spin rewards on startup
    /*
    @Bean
    CommandLineRunner seedSpinRewards(SpinRewardRepository repo) {
        return args -> {
            try {
                if (repo.count() > 0) return;

                List<SpinReward> rewards = List.of(
                        SpinReward.builder().label("Chúc bạn may mắn").type(SpinRewardType.MESSAGE).value("Chúc bạn may mắn lần sau 🍀").weight(40).active(true).build(),
                        SpinReward.builder().label("Voucher 5%").type(SpinRewardType.VOUCHER).value("VOUCHER5").weight(25).active(true).build(),
                        SpinReward.builder().label("Voucher 10%").type(SpinRewardType.VOUCHER).value("NEWYEAR1").weight(15).active(true).build(),
                        SpinReward.builder().label("Freeship").type(SpinRewardType.VOUCHER).value("FREESHIP").weight(10).active(true).build(),
                        SpinReward.builder().label("Voucher 15%").type(SpinRewardType.VOUCHER).value("VOUCHER15").weight(7).active(true).build(),
                        SpinReward.builder().label("Quà Sticker").type(SpinRewardType.GIFT).value("Sticker ElectroMart 😄").weight(3).active(true).build()
                );

                repo.saveAll(rewards);
            } catch (Exception e) {
                System.out.println("Warning: Seed data failed - " + e.getMessage());
            }
        };
    }
    */
}
