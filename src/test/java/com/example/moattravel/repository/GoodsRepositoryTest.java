package com.example.moattravel.repository;

import com.example.moattravel.entity.Goods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration; // ★この行を削除
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(classes = GoodsRepositoryTest.TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableTransactionManagement
@DisplayName("GoodsRepositoryのテスト")
class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Configuration
    @EnableJpaRepositories(basePackageClasses = GoodsRepository.class)
    @EntityScan(basePackageClasses = Goods.class)
    // ★ここから @EnableAutoConfiguration アノテーションを削除する
    // @EnableAutoConfiguration // この行を削除
    static class TestConfig {

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            dataSource.setUsername("sa");
            dataSource.setPassword("");
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource);
            em.setPackagesToScan("com.example.moattravel.entity");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            em.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop");
            em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            return em;
        }

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            return transactionManager;
        }
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Goodsエンティティを保存できること")
    void testSaveGoods() {
        Goods goods = new Goods();
        goods.setName("テスト商品A");
        goods.setPrice(1000);
        goods.setCategory("食品");
        goods.setDescription("美味しいです");
        goods.setOrderCapacity(100);
        goods.setStock(500);
        goods.setImageUrl("URL1");

        Goods savedGoods = goodsRepository.save(goods);

        Optional<Goods> foundGoodsOptional = goodsRepository.findById(savedGoods.getId());

        assertThat(foundGoodsOptional).isPresent();
        Goods foundGoods = foundGoodsOptional.get();

        assertThat(foundGoods.getId()).isNotNull();
        assertThat(foundGoods.getName()).isEqualTo("テスト商品A");
        assertThat(foundGoods.getPrice()).isEqualTo(1000);
        assertThat(foundGoods.getCategory()).isEqualTo("食品");
        assertThat(foundGoods.getDescription()).isEqualTo("美味しいです");
        assertThat(foundGoods.getOrderCapacity()).isEqualTo(100);
        assertThat(foundGoods.getStock()).isEqualTo(500);
        assertThat(foundGoods.getImageUrl()).isEqualTo("URL1");
        assertThat(foundGoods.getCreatedAt()).isNotNull();
        assertThat(foundGoods.getUpdatedAt()).isNotNull();
    }

    @Test
    void testFindByNameContainingIgnoreCaseString() { fail("まだ実装されていません"); }
    @Test
    void testFindByNameContainingIgnoreCaseStringPageable() { fail("まだ実装されていません"); }
    @Test
    void testFindAllPageable() { fail("まだ実装されていません"); }
    @Test
    void testFindByCategoryAndNameContainingIgnoreCase() { fail("まだ実装されていません"); }
    @Test
    void testFindByCategory() { fail("まだ実装されていません"); }
}