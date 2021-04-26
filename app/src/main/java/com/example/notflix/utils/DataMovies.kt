package com.example.notflix.utils

import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity

object DataMovies {
//    fun generateDataMovies() : ArrayList<MoviesEntity> {
//        val moviesEntity = ArrayList<MoviesEntity>()
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_001",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
//                title = "A Star Is Born",
//                overview = "Seasoned musician Jackson Maine discovers — and falls in love with — " +
//                        "struggling artist Ally. She has just about given up on her dream to make it big as a " +
//                        "singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, " +
//                        "the personal side of their relationship is breaking down, as Jack fights an ongoing " +
//                        "battle with his own internal demons.",
//                genre = "Drama,Romance,Music",
//                country = "US",
//                rating = "7/10",
//                duration = "2h 16m"
//        ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_002",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/672kUEMtTHcaVYSVY4eiHEliHFa.jpg",
//                title = "Spectre",
//                overview = "A cryptic message from Bond’s past sends him on a trail to uncover a " +
//                        "sinister organization. While M battles political forces to keep the secret " +
//                        "service alive, Bond peels back the layers of deceit to reveal the terrible " +
//                        "truth behind SPECTRE.",
//                genre = "Action,Adventure,Thriller",
//                country = "US",
//                rating = "6.5/10",
//                duration = "2h 28m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_003",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mlhMgEEFdFql5qJJM8y73bFawbT.jpg",
//                title = "Mission: Impossible - Fallout",
//                overview = "When an IMF mission ends badly, the world is faced with dire consequences. As Ethan Hunt takes it upon himself to fulfill his original briefing, the CIA begin to question his loyalty and his motives. The IMF team find themselves in a race against time, hunted by assassins while trying to prevent a global catastrophe",
//                genre = "Action, Adventure",
//                country = "US",
//                rating = "7.4/10",
//                duration = "2h 27m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_004",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/asmxTLzVSLTkIwybSB0DJHDKw8o.jpg",
//                title = "Godzilla vs. Kong",
//                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
//                genre = "Action, Science Fiction",
//                country = "US",
//                rating = "8.3/10",
//                duration = "1h 53m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_005",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7wTiwf4xjSzoklR37htoEiFrmbS.jpg",
//                title = "John Wick: Chapter 3 - Parabellum",
//                overview = "Super-assassin John Wick returns with a \$14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
//                genre = "Action, Thriller, Crime",
//                country = "US",
//                rating = "7.4/10",
//                duration = "2h 10m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_006",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8yhtzsbBExY8mUct2GOk4LDDuGH.jpg",
//                title = "Mortal Kombat",
//                overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
//                genre = "Fantasy, Action, Adventure",
//                country = "US",
//                rating = "7.5/10",
//                duration = "1h 50m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_007",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7Zhv1JBcCmP8cf6mcbAYmnrgiIu.jpg",
//                title = "After We Collided",
//                overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
//                genre = "Romance, Drama",
//                country = "US",
//                rating = "7.3/10",
//                duration = "1h 40m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_008",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/p9YDHJKvUoi7v2SCd3vLGPae1Xp.jpg",
//                title = "Space Sweepers",
//                overview = "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
//                genre = "Drama, Fantasy, Science Fiction",
//                country = "US",
//                rating = "7.2/10",
//                duration = "2h 16m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_009",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
//                title = "Joker",
//                overview = "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
//                genre = "Crime, Thriller, Drama",
//                country = "US",
//                rating = "8.2/10",
//                duration = "2h 2m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_010",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKnhEsjNefpKnUdAkn7INzIFLSu.jpg",
//                title = "I Care a Lot",
//                overview = "A court-appointed legal guardian defrauds her older clients and traps them under her care. But her latest mark comes with some unexpected baggage.",
//                genre = "Comedy, Crime, Thriller",
//                country = "US",
//                rating = "6.7/10",
//                duration = "1h 59m"
//            ))
//        moviesEntity.add(
//            MoviesEntity(
//                id_movies = "mv_011",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nxxuWC32Y6TULj4VnVwMPUFLIpK.jpg",
//                title = "Seobok",
//                overview = "A former intelligence agent gets involved with the first human clone, Seo Bok, who others seek, causing trouble.",
//                genre = "Science Fiction, Action\n",
//                country = "KR",
//                rating = "5.0/10",
//                duration = "1h 54m"
//            ))
//
//        return moviesEntity
//    }
//
//    fun generateDataTvShow() : ArrayList<TvShowEntity> {
//        val tvshowEntity = ArrayList<TvShowEntity>()
//        tvshowEntity.add(
//           TvShowEntity(
//                "tv_001",
//               poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hxJQ3A2wtreuWDnVBbzzXI3YlOE.jpg",
//               title = "Start-Up",
//               overview = "Young entrepreneurs aspiring to launch virtual dreams into reality compete for success and love in the cutthroat world of Korea's high-tech industry.",
//               genre = "Drama, Comedy",
//               country = "KR",
//               rating = "8.4/10",
//               duration = "1h 12m",
//               total_eps = "16 eps"
//           )
//        )
//        tvshowEntity.add(
//            TvShowEntity(
//            "tv_002",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/MoEKaPFHABtA1xKoOteirGaHl1.jpg",
//                title = "Money Heist",
//                overview = "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
//                genre = "Crime, Drama",
//                country = "ESP",
//                rating = "8.3/10",
//                duration = "1h 10m",
//                total_eps = "15 eps"
//            )
//        )
//        tvshowEntity.add(
//            TvShowEntity(
//                 "tv_003",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
//                title = "Game of Thrones - Season 8",
//                overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
//                genre = "Sci-Fi & Fantasy, Drama, Action & Adventure",
//                country = "UK",
//                rating = "8.4/10",
//                duration = "1h",
//                total_eps = "6 eps"
//            )
//        )
//        tvshowEntity.add(
//            TvShowEntity(
//                 "tv_004",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
//                title = "Elite - Season 3",
//                overview = "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
//                genre = "Crime, Mystery, Drama",
//                country = "US",
//                rating = "8.2/10",
//                duration = "1h",
//                total_eps = "8 eps"
//            )
//        )
//        tvshowEntity.add(
//            TvShowEntity(
//                 "tv_005",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
//                title = "The Walking Dead",
//                overview = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
//                genre = "Action & Adventure, Drama, Sci-Fi & Fantasy",
//                country = "US",
//                rating = "8.1/10",
//                duration = "42m",
//                total_eps = "10 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_006",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
//                title = "The Queen's Gambit",
//                overview = "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.",
//                genre = "Drama",
//                country = "US",
//                rating = "8.7/10",
//                duration = "1h",
//                total_eps = "7 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_007",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8bizZsXoAsOTbhyFKfBogC8mgG2.jpg",
//                title = "Hotel Del Luna",
//                overview = "The Hotel Del Luna, located in Seoul, is not like any other hotel: its clients are all ghosts. Jang Man-wol, stuck in the hotel for the past millennium, meets Goo Chan-sung, the new manager.",
//                genre = "Sci-Fi & Fantasy, Drama, Comedy",
//                country = "KR",
//                rating = "8.9/10",
//                duration = "1h 10m",
//                total_eps = "16 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_008",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6zBWSuYW3Ps1nTfeMS8siS4KUaA.jpg",
//                title = "Riverdale - Season 3",
//                overview = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
//                genre = "Mystery, Drama, Crime",
//                country = "US",
//                rating = "8.6/10",
//                duration = "45m",
//                total_eps = "11 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_009",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wHIMMLFsk32wIzDmawWkYVbxFCS.jpg",
//                title = "The 100 - Season 7",
//                overview = "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
//                genre = "Sci-Fi & Fantasy, Drama, Action & Adventure",
//                country = "US",
//                rating = "7.9/10",
//                duration = "43m",
//                total_eps = "16 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_010",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg",
//                title = "Stranger Things - Season 3",
//                overview = "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
//                genre = "Sci-Fi & Fantasy, Mystery, Drama",
//                country = "US",
//                rating = "8.6/10",
//                duration = "50m",
//                total_eps = "8 eps"
//            ))
//        tvshowEntity.add(
//            TvShowEntity(
//                "tv_011",
//                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGdz67d5WHIU7kIqVHO2TxIpmhZ.jpg",
//                title = "Attack on Titan: No Regrets",
//                overview = "As Levi and Erwin cross paths, Erwin acknowledges Levi's agility and skill and gives him the option to either become part of the expedition team, or be turned over to the Military Police, to atone for his crimes. ",
//                genre = "Sci-Fi & Fantasy, Action & Adventure, Animation",
//                country = "JPN",
//                rating = "8.9/10",
//                duration = "27m",
//                total_eps = "2 eps"
//            ))
//
//        return tvshowEntity
//    }
//
//    fun generateEpisodes() : ArrayList<EpisodesEntity>{
//        val eps = ArrayList<EpisodesEntity>()
//        eps.add(
//            EpisodesEntity(
//                thumnail = "https://images.unsplash.com/photo-1527542146552-9e862c46e596?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80",
//            "Episode 1 : The beginning",
//            "Amet minim mollit non desemollit. Exercitation veniam consequat sunt nostrud amet."
//        )
//        )
//
//        eps.add(EpisodesEntity(
//            thumnail = "https://images.unsplash.com/photo-1527542146552-9e862c46e596?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80",
//            "Episode 2 : The 2nd beginning",
//            "Amet minim mollit non desemollit. Exercitation veniam consequat sunt nostrud amet."
//        ))
//        eps.add(EpisodesEntity(
//            thumnail = "https://images.unsplash.com/photo-1527542146552-9e862c46e596?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80",
//
//            "Episode 3 : The 3nd beginning",
//            "Amet minim mollit non desemollit. Exercitation veniam consequat sunt nostrud amet."
//        ))
//        eps.add(EpisodesEntity(
//            thumnail = "https://images.unsplash.com/photo-1527542146552-9e862c46e596?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80",
//
//            "Episode 4 : Plot twist",
//            "Amet minim mollit non desemollit. Exercitation veniam consequat sunt nostrud amet."
//        ))
//        eps.add(EpisodesEntity(
//            thumnail = "https://images.unsplash.com/photo-1527542146552-9e862c46e596?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80",
//
//            "Episode 5 : Clifhanger ending",
//            "Amet minim mollit non desemollit. Exercitation veniam consequat sunt nostrud amet."
//        ))
//
//        return eps
//    }
}