package practice.effective.chooseyourhero.repositories

import practice.effective.chooseyourhero.models.Hero

class HeroesRepository {
    private var heroesList: List<Hero> = listOf(
        Hero(
            "e5eea456-0d51-47ec-841d-e834569c1034", "Apocalypse", "I'm En Sabah Nur",
            "https://wikicomics.ru/uploads/posts/2020-08/1596609645_apokalipsis-apocalypse-en-sabah-nur.jpg"
        ),
        Hero(
            "f5e27497-4726-4b64-baa5-2459933cec54", "Captain America", "I'm Steven Rogers",
            "https://wikicomics.ru/uploads/posts/2020-08/1597208216_kapitan-amerika-captain-america-stiven-rodzhers.jpg"
        ),
        Hero(
            "31aea5c4-7386-4cd8-ae08-b89aa8985f35", "Deadpool", "I'm Wade Wilson",
            "https://wikicomics.ru/uploads/posts/2020-07/1596058322_dedpul.jpg"
        ),
        Hero(
            "7be3499d-f772-41a9-a7c8-8511b223b823", "Iron Man", "I'm Tony Stark",
            "https://wikicomics.ru/uploads/posts/2020-08/1597206384_zheleznyy-chelovek-iron-man-toni-stark.jpg"
        ),
        Hero(
            "79d7e614-df5f-42c8-baf4-5257c5865804", "Nebula", "I'm Nebula",
            "https://wikicomics.ru/uploads/posts/2020-08/1596241378_nebula.jpg"
        ),
        Hero(
            "79e212bc-df37-4dd4-8e31-18154f7080f2", "Peggy Carter", "I'm Margaret Carter",
            "https://wikicomics.ru/uploads/posts/2020-08/1597089673_peggi-karter-peggy-carter-margaret-peggi-karter.jpg"
        ),
        Hero(
            "c2ec292a-3c9f-4710-946a-a4827b6ca553",
            "Scarlet Witch",
            "I'm Wanda MaximoffI'm Wanda MaximoffI'm Wanda MaximoffI'm Wanda MaximoffI'm Wanda Maximoff",
            "https://wikicomics.ru/uploads/posts/2020-08/1597203769_alaya-vedma-scarlet-witch-vanda-maksimoff.jpg"
        ),
        Hero(
            "3eb81f28-d41b-4dbb-bc69-e7bc938704ce", "Sif", "I'm Lady Sif",
            "https://wikicomics.ru/uploads/posts/2020-08/1597486125_sif-sif.jpg"
        ),
        Hero(
            "28d81894-f614-4daa-a2d0-43917c73729b", "Spiderman", "I'm Peter Parker",
            "https://wikicomics.ru/uploads/posts/2020-08/1597302956_chelovek-pauk-spider-man-piter-parker.jpg"
        ),
        Hero(
            "9e4f58e7-8555-4229-bc7e-300827809678", "Storm", "I'm Ororo Munroe",
            "https://wikicomics.ru/uploads/posts/2020-08/1597476240_shtorm-storm-ororo-monro.jpg"
        ),
    )

    internal fun getHeroesList(): List<Hero> = heroesList
}
