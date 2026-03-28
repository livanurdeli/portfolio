package com.example.portfolio;

import com.example.portfolio.model.Project;
import com.example.portfolio.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ProjectService projectService) {
        return args -> {
            if (projectService.getAllProjects().isEmpty()) {
                projectService.saveProject(new Project("bisiklet-kiralama-otomasyonu", "C# Windows Form veya WPF ile tasarlanmış bisiklet kiralama otomasyonu.", "C#", "https://github.com/livanurdeli/bisiklet-kiralama-otomasyonu"));
                projectService.saveProject(new Project("restoran-tanitim-sitesi", "Restoranlar için HTML/CSS tabanlı şık bir web tasarım örneği.", "HTML, CSS, C#, JavaScript", "https://github.com/livanurdeli/restoran-tanitim-sitesi"));
                projectService.saveProject(new Project("Skill-Matching-System", "Kullanıcıları yeteneklerine göre doğru iş fırsatlarına yönlendiren ilk algoritma.", "Java, HTML", "https://github.com/livanurdeli/Skill-Matching-System"));
                projectService.saveProject(new Project("Skill-Matching-System-v2", "Kurumsal tasarım iyileştirmeleri eklenmiş, daha kapsamlı yetenek eşleştirme platformu.", "CSS, Java, HTML, JavaScript", "https://github.com/livanurdeli/Skill-Matching-System-v2"));
                projectService.saveProject(new Project("spring-boot-kullanici-api", "Modern REST standartlarında yazılmış, veri tabanı bağlantılı Spring Boot API.", "Java", "https://github.com/livanurdeli/spring-boot-kullanici-api"));
                System.out.println("Started with complete GitHub repositories list including multi-languages.");
            }
        };
    }
}
