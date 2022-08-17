package com.example.assignment.entity

import com.example.assignment.dashboard.artileModels.Article
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class EntityTest {

    @Test
    fun `Test Id`() {
        val item = Article(id = 11233)
        assertEquals(item.id, 11233)
    }

    @Test
    fun `Test title`() {
        val item = Article(title = "Olivia")
        assertEquals(item.title, "Olivia")
    }

    @Test
    fun `Test publish date`() {
        val item = Article(publishedDate = "12,Mar 2022")
        assertEquals(item.publishedDate, "12,Mar 2022")
    }

    @Test
    fun `Test abstract`() {
        val item = Article(abstractArticle = "By Anthem Ham")
        assertEquals(item.abstractArticle, "By Anthem Ham")
    }
}