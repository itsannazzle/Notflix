package com.example.notflix.utils

import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity

object DataMovies {
    fun generateDataMovies() : ArrayList<MoviesEntity> {
        val moviesEntity = ArrayList<MoviesEntity>()
        moviesEntity.add(
            MoviesEntity(
                id_movies = "mv_001",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                title = "A Star Is Born",
                overview = "Seasoned musician Jackson Maine discovers — and falls in love with — " +
                        "struggling artist Ally. She has just about given up on her dream to make it big as a " +
                        "singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, " +
                        "the personal side of their relationship is breaking down, as Jack fights an ongoing " +
                        "battle with his own internal demons.",
                genre = "Drama,Romance,Music",
                country = "US",
                rating = "7/10",
                duration = "2h 16m"
        ))
        moviesEntity.add(
            MoviesEntity(
                id_movies = "mv_002",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/672kUEMtTHcaVYSVY4eiHEliHFa.jpg",
                title = "Spectre",
                overview = "A cryptic message from Bond’s past sends him on a trail to uncover a " +
                        "sinister organization. While M battles political forces to keep the secret " +
                        "service alive, Bond peels back the layers of deceit to reveal the terrible " +
                        "truth behind SPECTRE.",
                genre = "Action,Adventure,Thriller",
                country = "US",
                rating = "6.5/10",
                duration = "2h 28m"
            ))
        moviesEntity.add(
            MoviesEntity(
                id_movies = "mv_003",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mlhMgEEFdFql5qJJM8y73bFawbT.jpg",
                title = "Mission: Impossible - Fallout",
                overview = "When an IMF mission ends badly, the world is faced with dire consequences. As Ethan Hunt takes it upon himself to fulfill his original briefing, the CIA begin to question his loyalty and his motives. The IMF team find themselves in a race against time, hunted by assassins while trying to prevent a global catastrophe",
                genre = "Action, Adventure",
                country = "US",
                rating = "7.4/10",
                duration = "2h 27m"
            ))
        moviesEntity.add(
            MoviesEntity(
                id_movies = "mv_004",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/asmxTLzVSLTkIwybSB0DJHDKw8o.jpg",
                title = "Godzilla vs. Kong",
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                genre = "Action, Science Fiction",
                country = "US",
                rating = "8.3/10",
                duration = "1h 53m"
            ))
        moviesEntity.add(
            MoviesEntity(
                id_movies = "mv_005",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7wTiwf4xjSzoklR37htoEiFrmbS.jpg",
                title = "John Wick: Chapter 3 - Parabellum",
                overview = "Super-assassin John Wick returns with a \$14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
                genre = "Action, Thriller, Crime",
                country = "US",
                rating = "7.4/10",
                duration = "2h 10m"
            ))

        return moviesEntity
    }

    fun generateDataTvShow() : ArrayList<TvShowEntity> {
        val tvshowEntity = ArrayList<TvShowEntity>()
        tvshowEntity.add(
           TvShowEntity(
               id_movies = "tv_001",
               poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hxJQ3A2wtreuWDnVBbzzXI3YlOE.jpg",
               title = "Start-Up",
               overview = "Young entrepreneurs aspiring to launch virtual dreams into reality compete for success and love in the cutthroat world of Korea's high-tech industry.",
               genre = "Drama, Comedy",
               country = "KR",
               rating = "8.4/10",
               duration = "1h 12m",
               total_eps = "16 eps"
           )
        )
        tvshowEntity.add(
            TvShowEntity(
                id_movies = "tv_002",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/MoEKaPFHABtA1xKoOteirGaHl1.jpg",
                title = "Money Heist",
                overview = "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
                genre = "Crime, Drama",
                country = "ESP",
                rating = "8.3/10",
                duration = "1h 10m",
                total_eps = "15 eps"
            )
        )
        tvshowEntity.add(
            TvShowEntity(
                id_movies = "tv_003",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                title = "Game of Thrones - Season 8",
                overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                genre = "Sci-Fi & Fantasy, Drama, Action & Adventure",
                country = "UK",
                rating = "8.4/10",
                duration = "1h",
                total_eps = "6 eps"
            )
        )
        tvshowEntity.add(
            TvShowEntity(
                id_movies = "tv_004",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
                title = "Elite - Season 3",
                overview = "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
                genre = "Crime, Mystery, Drama",
                country = "US",
                rating = "8.2/10",
                duration = "1h",
                total_eps = "8 eps"
            )
        )
        tvshowEntity.add(
            TvShowEntity(
                id_movies = "tv_005",
                poster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                title = "The Walking Dead",
                overview = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                genre = "Action & Adventure, Drama, Sci-Fi & Fantasy",
                country = "US",
                rating = "8.1/10",
                duration = "42m",
                total_eps = "10 eps"
            ))

        return tvshowEntity
    }

    fun generateEpisodes(tvShowId : String) : ArrayList<EpisodesEntity>{
        val eps = ArrayList<EpisodesEntity>()
        eps.add(
            EpisodesEntity(
            tvShowId,
            "Episode 1 : i dont know",
            0,
            "Lorem"
        )
        )

        eps.add(EpisodesEntity(
            tvShowId,
            "Episode 2 : i dont know",
            1,
            "Lorem"
        ))
        eps.add(EpisodesEntity(
            tvShowId,
            "Episode 3 : i dont know",
            2,
            "Lorem"
        ))
        eps.add(EpisodesEntity(
            tvShowId,
            "Episode 4 : i dont know",
            3,
            "Lorem"
        ))
        eps.add(EpisodesEntity(
            tvShowId,
            "Episode 5 : i dont know",
            4,
            "Lorem"
        ))

        return eps
    }
}