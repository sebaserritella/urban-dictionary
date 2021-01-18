package com.urbandictionary.database

import com.urbandictionary.data.database.UrbanDao
import com.urbandictionary.domain.model.Urban
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

        val loadFromDB = urbanDao.getDefine("pepa")
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = mockUrban()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    private fun mockUrban() = Urban(
        word = "pepa",
        defid = 1,
        author = "juan",
        current_vote = "",
        example = "",
        searchWord = "pepa",
        permalink = "",
        definition = "",
        written_on = "",
        sound_urls = listOf(),
        thumbs_down = 1,
        thumbs_up = 1
    )

    private fun mockUrbanList() = listOf(mockUrban())
}
