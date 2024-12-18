package com.example.demo;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.PermissionEntity;
import com.example.demo.models.RoleEntity;
import com.example.demo.models.RoleEnum;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.RepositoryUser;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	CommandLineRunner init(RepositoryUser repositoryUser){
		return args -> {
			/* CREATE PERMISSIONS */
			PermissionEntity createPermission = PermissionEntity.builder()
			.name("CREATE")
			.build();

			PermissionEntity readPermission = PermissionEntity.builder()
			.name("READ")
			.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
			.name("UPDATE")
			.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
			.name("REFACTOR")
			.build();

			/* CREATE ROLES */
			RoleEntity roleAdmin = RoleEntity.builder()
			.roleEnum(RoleEnum.ADMIN)
			.permissionList(Set.of(createPermission, readPermission, updatePermission, refactorPermission))
			.build();

			RoleEntity roleUser = RoleEntity.builder()
			.roleEnum(RoleEnum.USER)
			.permissionList(Set.of(readPermission, createPermission))
			.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
			.roleEnum(RoleEnum.DEVELOPER)
			.permissionList(Set.of(createPermission, readPermission, updatePermission, refactorPermission))
			.build();

			RoleEntity roleInvited = RoleEntity.builder()
			.roleEnum(RoleEnum.INVITED)
			.permissionList(Set.of(readPermission))
			.build();

			/* CREATE USERS */
			UserEntity userUser = UserEntity.builder()
			.username("user")
			.password("password")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.roles(Set.of(roleUser))
			.build();

			UserEntity userAdmin = UserEntity.builder()
			.username("admin")
			.password("password")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.roles(Set.of(roleAdmin))
			.build();

			UserEntity userDeveloper = UserEntity.builder()
			.username("developer")
			.password("password")
			.isEnabled(true)
			.accountNoExpired(true)
			.accountNoLocked(true)
			.roles(Set.of(roleDeveloper))
			.build();

			repositoryUser.saveAll(List.of(userDeveloper, userAdmin, userUser));
		};
	}
}
