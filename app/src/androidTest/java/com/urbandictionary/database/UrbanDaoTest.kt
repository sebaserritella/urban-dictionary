package com.urbandictionary.database

import com.urbandictionary.data.database.dao.UrbanDao
import com.urbandictionary.data.database.entities.UrbanEntity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UrbanDaoTest : AppDatabase() {

    private lateinit var urbanDao: UrbanDao

    @Before
    fun init() {
        urbanDao = this.db.urbanDao()
    }

    @Test
    fun insertAndLoadPosterListTest() {
        val mockDataList = mockUrbanList()
        urbanDao.insertAllUrban(mockDataList)

        val loadFromDB = urbanDao.getDefine("hola")
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = mockUrban()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    private fun mockUrban() = UrbanEntity(
        word = "hola",
        defid = 1,
        author = "juan",
        current_vote = "",
        example = "",
        searchWord = "hola",
        permalink = "",
        definition = "",
        written_on = "",
        sound_urls = listOf(),
        thumbs_down = 1,
        thumbs_up = 1
    )

    private fun mockUrbanList() = listOf(mockUrban())
}
