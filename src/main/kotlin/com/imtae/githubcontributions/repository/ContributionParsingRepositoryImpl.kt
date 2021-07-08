package com.imtae.githubcontributions.repository

import com.imtae.githubcontributions.domain.Contribution
import com.imtae.githubcontributions.domain.Contributions
import com.imtae.githubcontributions.domain.Range
import com.imtae.githubcontributions.domain.Year
import com.imtae.githubcontributions.key.Type
import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@Component
class ContributionParsingRepositoryImpl : ContributionParsingRepository {

    private val _yearList = arrayListOf<Year>()
    private val _contributionsList = arrayListOf<Contributions>()

    override fun getContributions(user: String): Contribution {

        val yearList = getContributionYears(user)
        _yearList.clear()
        _contributionsList.clear()

        return getContributionData(yearList)
    }

    override fun getContribution(user: String, date: String): Any = getContributionData(user, date)

    override fun getTodayContribution(user: String): Any = getContributionData(user)

    private fun getContributionYears(user: String): ArrayList<String> {

        val doc = Jsoup.connect("https://github.com/$user")
                .userAgent("Mozilla")
                .timeout(10000)
                .ignoreHttpErrors(true)
                .get()

        val years = doc.select(".js-year-link")
        val yearLinkList = arrayListOf<String>()

        years.forEach{
            yearLinkList.add(it.attr("href").trim())
        }

        return yearLinkList
    }

    private fun getContributionData(yearLinkList : ArrayList<String>): Contribution {

        for (yearLink in yearLinkList) {

            val doc = Jsoup.connect("https://github.com$yearLink").ignoreHttpErrors(true).get()

            val contributionText =
                    doc.select(".js-yearly-contributions h2").text()
                            .replace("[ ]".toRegex(), "")
                            .split("[a-z|A-Z]".toRegex())

            println(contributionText)

            println(doc.select(".js-yearly-contributions h2").text())

            val year = contributionText[contributionText.lastIndex].ifBlank { SimpleDateFormat("yyyy").format(Calendar.getInstance().time) } // 2020, 2019 ...
            val total = Integer.parseInt(contributionText[0].replace(",", "")) // 550, 140 ...

            val contributions = doc.select("rect.ContributionCalendar-day")

            val contributionList = arrayListOf<Contributions>()

            for (contribution in contributions.indices) {

                val fill = checkFillColor(contributions[contribution].attr("data-level").toString())
                val dataCount = contributions[contribution].attr("data-count")
                val dataDate = contributions[contribution].attr("data-date")

                if (!dataCount.isNullOrEmpty()) contributionList.add(Contributions(dataDate, Integer.parseInt(dataCount), fill))
            }

            _yearList.add(Year(year, total, Range(contributionList[0].date!!, contributionList[contributionList.size -1].date!!)))

            for (contribution in contributionList)
                _contributionsList.add(contribution)
        }

        return Contribution(_yearList, _contributionsList)
    }

    private val checkDateValid = { date: String, type: Type ->
        when {
            date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\$".toRegex()) && type == Type.DATE -> true
            date.matches("^\\d{4}".toRegex()) && type == Type.YEAR -> true
            else -> false
        }
    }

    private fun getContributionData(user: String, date: String? = null): Any {

        val currentYear = SimpleDateFormat("yyyy").format(Calendar.getInstance().time)
        val todayDate = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)

        println(date)

        val yearPath: String = try {
            date?.slice(0..3).toString()
        } catch (e: Exception) {
            currentYear
        }

        val connection =
                when {
                    date == null || checkDateValid(date, Type.DATE) -> Jsoup.connect("https://github.com/$user?tab=overview&from=$yearPath-01-01&to=$yearPath-12-31")
                    checkDateValid(date, Type.YEAR) -> Jsoup.connect("https://github.com/$user?tab=overview&from=$date-01-01&to=$date-12-31")
                    else -> return Contributions()
                }

        val doc =
                connection
                        .userAgent("Mozilla")
                        .timeout(10000)
                        .ignoreHttpErrors(true)
                        .get()

        val contributions = doc.select("rect.ContributionCalendar-day")

        val contributionList = arrayListOf<Contributions>()

        for (contribution in contributions.indices) {

            val fill = checkFillColor(contributions[contribution].attr("data-level"))
            val dataCount = contributions[contribution].attr("data-count")
            val dataDate = contributions[contribution].attr("data-date")

            if (contributions[contribution].attr("data-date") == date ?: todayDate) {

                return Contributions(dataDate, Integer.parseInt(dataCount), fill)
            } else if (!dataCount.isNullOrEmpty()) contributionList.add(Contributions(dataDate, Integer.parseInt(dataCount), fill))
        }
        return if (contributionList.size > 0) contributionList else Contributions()
    }

    private fun checkFillColor(fill: String?): String? {

        return when (fill) {
            "var(--color-calendar-graph-day-bg)", "0" -> "#e6e8ed"
            "var(--color-calendar-graph-day-L1-bg)", "1" -> "#8ce797"
            "var(--color-calendar-graph-day-L2-bg)", "2" -> "#38bc50"
            "var(--color-calendar-graph-day-L3-bg)", "3" -> "#2c933d"
            "var(--color-calendar-graph-day-L4-bg)", "4" -> "#1d5d2b"
            "var(--color-calendar-halloween-graph-day-l1-bg)" -> "#ffee4a"
            "var(--color-calendar-halloween-graph-day-l2-bg)" -> "#ffc501"
            "var(--color-calendar-halloween-graph-day-l3-bg)" -> "#fe9600"
            "var(--color-calendar-halloween-graph-day-l4-bg)" -> "#03001c"
            else -> fill
        }
    }

    // 추가 예정
    private fun changeFillColor(color: String?): String? {
        return null
    }
}