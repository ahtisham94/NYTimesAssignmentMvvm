package com.example.assignment.entity

import com.example.assignment.dashboard.artileModels.Article
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class EntityTest {

    @Test
    fun testArticleId() {
        val item = Article(id = 11233)
        assertEquals(item.id, 11233)
    }

    @Test
    fun testArticleTitle() {
        val item = Article(title = "Olivia")
        assertEquals(item.title, "Olivia")
    }

    @Test
    fun testArticlePublishedDate() {
        val item = Article(publishedDate = "12,Mar 2022")
        assertEquals(item.publishedDate, "12,Mar 2022")
    }

    @Test
    fun testArticleAbstract() {
        val item = Article(abstractArticle = "By Anthem Ham")
        assertEquals(item.abstractArticle, "By Anthem Ham")
    }
}