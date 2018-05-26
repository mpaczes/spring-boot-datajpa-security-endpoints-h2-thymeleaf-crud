package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	Post findFirstByOrderByPostedOnDesc();
	
	List<Post> findAllByOrderByPostedOnDesc();

	Post findBySlug(String slug);

	List<Post> findAllByAuthorIdOrderByPostedOnDesc(Long id);
	
	// 'custom dynamic queries in spring data jpa'
	// dodalem to z innego kursu 'Introduction to Spring Data JPA' (ponizsze metody nie sa uzywane w serwisach) :
	
	// AUTHOR :
	
	List<Post> findAllByauthorFirstName(String first);
	
	List<Post> findAllByAuthorFirstNameIgnoreCase(String first);
	
	List<Post> findAllByAuthorFirstNameIgnoreCaseOrderByPostedOnDesc(String first);
	
	List<Post> findAllByAuthorFirstNameIgnoreCaseAndAuthorLastNameIgnoreCase(String first, String last);
	
	// KEYWORDS :
	
	List<Post> findAllByKeywords(String keyword);
	
	// wywolanie tego zeby zadzialalo : postRepository.findAllByKeywordsLikeIgnoreCase('%' + keyword + '%');
	List<Post> findAllByKeywordsLikeIgnoreCase(String keyword);
	
	// ACTIVE :
	
	// aktywne posty :
	List<Post> findAllByactiveTrue();
	
	// AUTHOR & KEYWORDS :
	
	List<Post> findAllByauthorFirstNameAndKeywords(String first, String keywords);
	
	List<Post> findAllByauthorFirstNameAndKeywordsOrderByPostedOnDesc(String first, String keywords);
	
	// QUERY :
	
	// 'slug' oznacza alias dla posta
	
	// JPA SQL albo inaczej Hibernate Query Language
	@Query("select p from Post p where p.slug = ?1")
	Post findPostBySlug(String slug);
	
	@Query("select p from Post p where p.slug = :slug")
	Post findPostBySlugNamedParam(@Param("slug") String slug);
	
	// normalny ('strict') SQL		-->> (konsola aplikacji)		-->>		select * from Post where slug = ?
	@Query( value = "select * from Post where slug = :slug", nativeQuery = true )
	Post findPostBySlugNative(@Param("slug") String slug);

}
