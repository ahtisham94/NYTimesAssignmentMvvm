package com.example.assignment.entity

import com.example.assignment.dashboard.artileModels.Article
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class EntityTest {

    @Test
    fun testArticleIdTest() {
        val item = Article(id = 11233)
        assertEquals(item.id, 11233)
    }

    @Test
    fun testUriIdTest() {
        val item = Article(uri = "TestingUri")
        assertEquals(item.uri, "TestingUri")
    }

    @Test
    fun testUrlIdTest() {
        val item = Article(url = "TestingUrl")
        assertEquals(item.url, "TestingUrl")
    }

    @Test
    fun testArticlePublishedDateTest() {
        val item = Article(publishedDate = "12,Mar 2022")
        assertEquals(item.publishedDate, "12,Mar 2022")
    }

    @Test
    fun testUpdatedTest() {
        val item = Article(updated = "TestingUpdated")
        assertEquals(item.updated, "TestingUpdated")
    }

    @Test
    fun testSectionTest() {
        val item = Article(section = "TestingSection")
        assertEquals(item.section, "TestingSection")
    }

    @Test
    fun testTypeTest() {
        val item = Article(type = "TestingType")
        assertEquals(item.type, "TestingType")
    }

    @Test
    fun testArticleTitleTest() {
        val item = Article(title = "Olivia")
        assertEquals(item.title, "Olivia")
    }

    @Test
    fun testArticleAbstractTest() {
        val item = Article(abstractArticle = "By Anthem Ham")
        assertEquals(item.abstractArticle, "By Anthem Ham")
    }

    @Test
    fun testByLineTest() {
        val item = Article(byline = "TestingByline")
        assertEquals(item.byline, "TestingByline")
    }
}