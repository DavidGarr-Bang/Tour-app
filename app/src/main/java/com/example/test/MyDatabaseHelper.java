package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydatebase"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database


    MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // on create method will run when the user doesn't have a database
    @Override
    public void onCreate(SQLiteDatabase db) {
        addMyDatabase(db, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        addMyDatabase(db,  oldVersion, newVersion);
    }



    // insert method for adding attractions to the data base
    private static void insertplace(SQLiteDatabase db, String name, String description, String tube, String location, double lon, double lan ,String chprice,String adprice, String information,String opentime,String website,
                                    int resourceId,int seconed_img) {
        ContentValues placeValues = new ContentValues();


        //Table columns
        placeValues.put("NAME", name);
        placeValues.put("DESCRIPTION", description);
        placeValues.put("IMAGE_RESOURCE_ID", resourceId);
        placeValues.put("WEB",website);
        placeValues.put("TUBE", tube);
        placeValues.put("LOCATION",location);
        placeValues.put("lon",lon);
        placeValues.put("lan",lan);
        placeValues.put("OPENTIME",opentime);
        placeValues.put("adPRICE",chprice);
        placeValues.put("chPRICE",adprice);
        placeValues.put("SCEIMAGE",seconed_img);
        placeValues.put("INFORMATION",information);

        db.insert("PLACE", null, placeValues);

    }
    // insert method for events zxc  attractions to the data base
    private static void insertEvent(SQLiteDatabase db, String name, String description, String date, String prodate, String location, String adprice,String chprice, String information,
                                    int resourceId) {

        ContentValues eventValues = new ContentValues();


        //Table columns
        eventValues.put("NAME", name);
        eventValues.put("DESCRIPTION", description);
        eventValues.put("IMAGE_RESOURCE_ID", resourceId);
        eventValues.put("DATE", date);
        eventValues.put("PRODATE", prodate);
        eventValues.put("LOCATION",location);
        eventValues.put("ADPRICE",adprice);
        eventValues.put("CHPRICE",chprice);
        eventValues.put("INFORMATION",information);

        db.insert("Event", null, eventValues);

    }


    // methoed for creating table and coloms to the database

    private void addMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            // create table Event with its coloums
            db.execSQL("CREATE TABLE Event (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "DATE,"
                    + "PRODATE,"
                    + "LOCATION, "
                    + "ADPRICE, "
                    + "CHPRICE, "
                    + "INFORMATION ); "
            );
            // create table Place withe its coloum
            db.execSQL("CREATE TABLE PLACE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "SCEIMAGE INTEGER, "
                    + "WEB,"
                    + "TUBE,"
                    + "LOCATION, "
                    + "lon,"
                    + "lan,"
                    + "OPENTIME, "
                    + "adPRICE, "
                    + "chPRICE, "
                    + "INFORMATION ); "
            );
            // iinserting events to the dataa
            insertEvent(db, "Hyde Park Winter Wonderland",
                    "Christmas family fun aplenty with a huge ice rink, two circuses, an observation wheel and the Magical Ice Kingdom, plus themed bars for the grown-ups",
                    "27 Dec 17",
                    "2018-12-27",
                    "London W2 4RU ",
                    "free",
                    "free",
                    "The Grinch would have a real job stealing all the Christmas from Hyde Park's massive tribute to festive fun, which is back for an eleventh year in 2017. Entry to Winter Wonderland is free, and a wander past the fairground rides, around child-friendly Santa Land (including Santa's grotto) or through the Christmas markets is a real treat for anyone feeling the spirit of the season, as long as you're ready to hear all those songs as you potter.\n" +
                            "\nOther attractions at Hyde Park's annual sparkly Christmassy addition include two circuses – ' Zippos Christmas Circus' and 'Cirque Berserk'. Winter Wonderland's ice rink, the biggest outdoor rink in the UK, surrounds the Victorian bandstand and is illuminated with more than 100,000 lights. Plus the return of the family-friendly 'Sooty Christmas Show' with Sweep, Soo and TV's Richard Cadell all making appearances.\n" +
                            "\nThis year the Magical Ice Kingdom presents an 'Deep Sea Adventure' - an immersive walk through experience filled with ice, snow sculptures and a bubble wall. Along with the 60-metre observation wheel, rollercoasters and fairground rides will keep thrill seekers happy. A good alternative for those who prefer to stay on solid ground are the selection of themed bars with real fires, except for the Ice Bar (for obvious structural reasons) where even the glasses you drink from are made of ice.\n" +
                            "\nIf you're skating, be aware that while there's no minimum age for skaters, under-12s must be accompanied by someone 16 or over and the smallest skates for hire are children's size 9 (adult skates go up to size 13). You can use your own skates as long as they're not speed skates. Wheelchair users are welcome on the ice. There are also ice guides who can look after groups of up to 15 skaters at a time (for an additional charge).\n",
                    R.drawable.winter);


            insertEvent(db, "New Year's Day Parade",
                    "London's New Year's Day Parade celebrates its 32nd year in 2018 and more than 8,500 performers representing 20 countries will take part, including Pearly Kings and Queens, cheerleaders, marching bands, dancers and representatives from West End shows. ",
                    "1st Jan 18",
                    "2018-01-01",
                    "Parliament Square, Westminster Bridge, Westminster, London SW1P 3JX",
                    "free",
                    "free",
                    "The American-style extravaganza of musicians – a number of bands from the USA fly over to take part – dancers, acrobats, clowns and floats twirl, march and drum a 2.2-mile route beginning at the Ritz Hotel, taking in Piccadilly Circus, Lower Regent Street, Pall Mall, Trafalgar Square, Whitehall and Big Ben, with temporary grandstands erected for better viewing in some places. Arrive early to secure a good vantage point, especially if you're taking small children, or book a ticket for one of the three grandstands to guarantee a good view (£30).\n\nA key part of the parade is 'The Let's Help London Challenge', in which all of the 32 London boroughs compete for a share of a prize pot to distribute among their chosen charities. Boroughs enter the parade with an act, float or other form of entertainment which is judged by an international panel comprising foreign ambassadors and high commissioners.",
                    R.drawable.new_year_event);


            insertEvent(db, "Skate at Somerset House",
                    "Get your skates on. Somerset House's grand 18th-century courtyard is the stunning setting for an ice rink again this winter. No matter how unimpressive your skating skills a trip to this 900-square-metre outdoor rink will be the very definition of festive fun. There are lots of tempting extras too: the Fortnum's Lodge offers champagne, mulled wine and winter dining; and Skate Lates will feature Peckham-based radio station Balamii, Field Day founders Eat Your Own Ears and Supa Dupa Fly.",
                    "14th Jan 18",
                    "2018-01-18",
                    "Strand London WC2R 1LA",
                    "£7.50 plus",
                    "£7.50 plus",
                    "Plus the Fortnum’s Lodge menu features winter classics like raclette, cheese fondue, smoked salmon and mince pies. The Christmas Arcade will bring posh shopping from Fortnum & Mason - this year with a focus on all things chocolate - and guests can admire the skaters with drink in hand at the Skate Lounge, both of which can be accessed without a ticket. Tickets are available from September 14.",
                    R.drawable.skate);


            insertEvent(db, "London Design Festival",
                    "Explore a range of creative installations and events celebrating contemporary design during the London Design Festival",
                    "15th & 26th Sep 18",
                    "2017-9-15",
                    "33 John St, London WC1N 2AT",
                    "free",
                    "free",
                    "The annual festival has been showcasing the work of designers, architects, artists and retailers since 2003.\n\n" +
                            "During the festival, hundreds of large-scale installations, exhibitions and events pop up in many unique spaces across London, from world-famous museums to small local studios. The V&A is the hub of the London Design Festival, hosting a wide range of activities, from talks and workshops, to installations and exhibitions.\n",
                    R.drawable.design);

            insertEvent(db, "London Literature Festival",
                    "The London Literature Festival once again fills the Southbank Centre with acclaimed authors, poets, speakers and collaborators for 20 days of wordy events. Celebrating its eleventh year in 2017, the festival will include live readings, talks and workshops from the likes of ‘His Dark Materials’ author Philip Pullman, acclaimed photographer Annie Leibovitz and the Man Booker Prize nominees. This year's festival opens with the 50th birthday of Poetry International, and sets out to explore how literature can remind us of our shared humanity.",
                    "1 - 30 SEP 17",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.lit);


            insertEvent(db, "Treasures of the Scythians", "Quick history lesson: Scythia was a region in antiquity that covered parts of present-day Ukraine, southern Russia and Kazakhstan. It was also home to a great nomadic civilisation that produced a great number of pretty astounding artefacts, which have been amassed for this exhibition: rugs, furs, horse headgear and gold items. It promises to be a dazzling insight into one a lesser-known chapter of history.",
                    "29 Oct 17",
                    "31 Dec 17",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.tre);

            insertEvent(db, "Conflict Café", "Feast on Syrian cuisine by London chefs from Ayam Zaman at this pop-up in House of Vans, Waterloo on Peace Day. Join communal tables, break bread with strangers, learn more about Syrian culture and loosen your belt for a nine-course sharing meal. Booking essential.",
                    "27 Aug 17",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.conf);

            insertEvent(db, "Jasper Johns","Indisputable fact: there's no more important painter alive today than Jasper Johns. The 86-year-old artist's influence stretches so far and wide that it's practically immeasurable. He came of age in 1950s New York, in the fertile years between splashy Abstract Expressionism and ice-cool Pop, which was when he created his iconic piece 'Flag', which nodded to both movements. Iconoclastic and experimental, Johns moved the goalposts of painting.",
                    "27 Aug 18",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.jas);


            insertEvent(db, "Catford Arts Trail","SE6 is bursting with creativity and you can catch a look at all of its arty genius on the Catford Arts Trail. Wind your way through south east London and see work from over 100 artists at 51 venues, including the homes of local makers, public spaces and even a graffiti workshop in the centre of town. Now in its second year, the trail will also see plenty of workshops and events so you can unleash your own creative spirit. See the full trail and artists involved here.",
                    "23 - 30 Sep 17",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.cat);

            insertEvent(db, "BBC Good Food’s Feast","Get royally stuffed by chomping down on salt beef beside a Beefeater as BBC Good Food's Feast makes its way to the Tower of London’s moat. Munch on street food, dine in pop-up restaurants or just make friends with a raven. Find out more and book here. ",
                    "22 - 24 Sep 17",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.tower_event);

            insertEvent(db, "The Big London Flea","You'll find more than 50 vintage stalls to hunt through at the Big London Flea, held at Dalston's former ABC cinema, now known as EPIC Dalston. Market traders will set up shop alongside locals clearing out their attic to sell pre-loved clothes, kitchenware, oddities and accessories. You might find a designer dress, a one-of-a–kind piece of furniture or just a stack of old Bunty comics. It all depends on how sharp your eyes are peeled.",
                    "24 Sep 17",
                    "",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.biglondon);


            insertEvent(db, "Haroon Mirza","Marking the ten-year anniversary of this Chalk Farm space, Mirza's commission will rework two of his previous pieces into a new installation that will reflect the architecture of what was once a Methodist chapel.",
                    "28 - 30 Sep 17",
                    "31 Dec 17",
                    "",
                    "",
                    "",
                    "",
                    R.drawable.hm);


            insertEvent(db, "Totally Thames Festival", "See London’s river Thames brought to life in a month-long season of events along the river’s 42-mile (68km) stretch during the annual Totally Thames festival.\n"+
                            "\n\nWalk along the Thames to see large-scale art installations, free exhibitions and live performances. Enjoy a concert below river-level or join an archaeological tour of the river's surroundings. See boats of all sizes cruise down the river, or get on the water yourself with watersports and boat parties.\n" +
                            "Check back nearer the time for the Totally Thames 2018 programme.\n",
                    "1 Mar 17",
                    "",
                    "",
                    "free",
                    "free",
                    "hi",
                    R.drawable.thames_fest);



            insertplace(db, "The London Eye"
                    ,"The London Eye is a giant Ferris wheel on the South Bank of the River Thames in London.\n" +
                            "\nThe structure is 443 feet (135 m) tall and the wheel has a diameter of 394 feet (120 m). When it opened to the public in 2000 it was the world's tallest Ferris wheel. Its height was surpassed by the 525-foot (160 m) Star of Nanchang in 2006, the 541-foot (165 m) Singapore Flyer in 2008, and the 550-foot (167.6 m) High Roller (Las Vegas) in 2014. Supported by an A-frame on one side only, unlike the taller Nanchang and Singapore wheels, the Eye is described by its operators as \"the world's tallest cantilevered observation wheel\".\n" +
                            "\nIt is Europe's tallest Ferris wheel and offered the highest public viewing point in London until it was superseded by the 804-foot (245 m) high observation deck on the 72nd floor of The Shard, which opened to the public on 1 February 2013. It is the most popular paid tourist attraction in the United Kingdom with over 3.75 million visitors annually, and has made many appearances in popular culture.\n" +
                            "\nThe London Eye adjoins the western end of Jubilee Gardens (previously the site of the former Dome of Discovery), on the South Bank of the River Thames between Westminster Bridge and Hungerford Bridge beside County Hall, in the London Borough of Lambeth.\n"
                    ,"Waterloo Station"
                    ,"Lambeth, London SE1 7PB"
                    ,51.503324
                    ,-0.119543
                    ,"£23.45"
                    ,"£18.95"
                    ,"Placing assured be if removed it besides on. Far shed each high read are men over day. Afraid we praise lively he suffer family estate is. Ample order up in of in ready. Timed blind had now those ought set often which. Or snug dull he show more true wish. No at many deny away miss evil. On in so indeed spirit an mother. Amounted old strictly but marianne admitted. People former is remove remain as. "
                    ,"Daily \n11:00 - 18:00"
                    ,"https://www.motorsport.com/"
                    , R.drawable.newlondon,R.drawable.eye_new);



            insertplace(db, "National Gallery"
                    ,"The National Gallery is an art museum in Trafalgar Square in the City of Westminster, in Central London. Founded in 1824, it houses a collection of over 2,300 paintings dating from the mid-13th century to 1900.\n" +
                            "\nThe Gallery is an exempt charity, and a non-departmental public body of the Department for Culture, Media and Sport. Its collection belongs to the government on behalf of the British public, and entry to the main collection is free of charge. It is among the most visited art museums in the world, after the Louvre, the British Museum, and the Metropolitan Museum of Art.\n" +
                            "\nUnlike comparable museums in continental Europe, the National Gallery was not formed by nationalising an existing royal or princely art collection. It came into being when the British government bought 38 paintings from the heirs of John Julius Angerstein, an insurance broker and patron of the arts, in 1824. After that initial purchase the Gallery was shaped mainly by its early directors, notably Sir Charles Lock Eastlake, and by private donations, which comprise two-thirds of the collection.\n" +
                            "\nThe resulting collection is small in size, compared with many European national galleries, but encyclopaedic in scope; most major developments in Western painting \"from Giotto to Cézanne\" are represented with important works. It used to be claimed that this was one of the few national galleries that had all its works on permanent exhibition, but this is no longer the case.\n" +
                            "\nThe present building, the third to house the National Gallery, was designed by William Wilkins from 1832 to 1838. Only the façade onto Trafalgar Square remains essentially unchanged from this time, as the building has been expanded piecemeal throughout its history. Wilkins's building was often criticised for the perceived weaknesses of its design and for its lack of space; the latter problem led to the establishment of the Tate Gallery for British art in 1897.\n" +
                            "\nThe Sainsbury Wing, an extension to the west by Robert Venturi and Denise Scott Brown, is a notable example of Postmodernist architecture in Britain. The current Director of the National Gallery is Gabriele Finaldi.\n"
                    ,"Charing Cross Station"
                    ,"The National Gallery, Trafalgar Square, London WC2N 5DN"
                    ,51.508356
                    ,-0.129545
                    ,"free"
                    ,"free"
                    ,""
                    ,"Daily: 10am–6pm\n\nFriday: 10am–9pm\n\nClosed\n1 January\n 24–26 December"
                    ,"https://www.nationalgallery.org.uk/visiting",
                    R.drawable.gallery,R.drawable.nat_gal);



            insertplace(db, "The Shard"
                    ,"The Shard, also referred to as the Shard of Glass, Shard London Bridge and formerly London Bridge Tower, is a 95-storey skyscraper in Southwark, London, that forms part of the London Bridge Quarter development. Standing 310 metres (1,020 ft) high, the Shard is the tallest building in the United Kingdom, the tallest building in the European Union, the fourth-tallest building in Europe and the 96th-tallest building in the world.  It is also the second-tallest free-standing structure in the United Kingdom, after the concrete tower of the Emley Moor transmitting station.\n" +
                            "\n" +
                            "\nThe Shard's construction began in March 2009; it was topped out on 30 March 2012 and inaugurated on 6 July 2012. Practical completion was achieved in November 2012. The tower's privately operated observation deck, The View from The Shard, was opened to the public on 1 February 2013.  The glass-clad pyramidal tower has 72 habitable floors, with a viewing gallery and open-air observation deck on the 72nd floor, at a height of 244.3 metres (802 ft). It was designed by the Italian architect Renzo Piano and replaced Southwark Towers, a 24-storey office block built on the site in 1975. The Shard was developed by Sellar Property Group on behalf of LBQ Ltd and is jointly owned by Sellar Property (5%) and the State of Qatar (95%).\n"
                    ,"London Bridge Station"
                    ,"32 London Bridge St, London SE1 9SG"
                    ,51.504502
                    ,-0.086500
                    ,"free"
                    ,"free"
                    ,""
                    ,"Mon - Fir\n\n10:00-22:00"
                    ,"https://www.theviewfromtheshard.com/en/book-tickets/"
                    ,R.drawable.shard,R.drawable.the_shard);


            insertplace(db, "Tower of London"
                    ,"Ten the hastened steepest feelings pleasant few surprise property. An brother he do colonel against minutes uncivil. Can how elinor warmly mrs basket marked. Led raising expense yet demesne weather musical. Me mr what park next busy ever. Elinor her his secure far twenty eat object. Late any far saw size want man. Which way you wrong add shall one. As guest right of he scale these. Horses nearer oh elinor of denote."
                    ,"Tower Hill Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.508115
                    ,-0.075949
                    ,"£21.50"
                    ,"£9.70"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"https://www.hrp.org.uk/tower-of-london/visit/tickets-and-prices/#gs.lA6iUug",
                    R.drawable.tower_london,R.drawable.tower_pic);


            insertplace(db, "The London Zoo"
                    ,"London Zoo is the world's oldest scientific zoo. It was opened in London on 27 April 1828, and was originally intended to be used as a collection for scientific study. In 1831 or 1832 the animals of the Tower of London menagerie were transferred to the zoo's collection. It was eventually opened to the public in 1847.Today it houses a collection of 698 species of animals, with 20,166 individuals, making it one of the largest collections in the United Kingdom. The zoo is sometimes called Regent's Zoo.\n" +
                            "\nIt is managed under the aegis of the Zoological Society of London (established in 1826), and is situated at the northern edge of Regent's Park, on the boundary line between the City of Westminster and the borough of Camden (the Regent's Canal runs through it). The Society also has a more spacious site at ZSL Whipsnade Zoo in Bedfordshire to which the larger animals such as elephants and rhinos have been moved. As well as being the first scientific zoo, ZSL London Zoo also opened the first Reptile house (1849), first public Aquarium (1853), first insect house (1881) and the first children's zoo (1938).\n" +
                            "\nZSL receives no state funding and relies on 'Fellows' and 'Friends' memberships, entrance fees and sponsorship to generate income.\n" +
                            "\nIn the early hours of 23 December 2017, The Animal Adventure section of the zoo suffered a severe fire, which apparently started in the cafeteria. The fire killed a nine-year-old aardvark, Misha, and four meerkats were missing, presumed dead. The zoo, however, will reopen on Christmas Eve.\n"
                    ,"Chalk Farm Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.535291
                    ,-0.153431
                    ,"£25"
                    ,"£19.50"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"https://www.zsl.org/zsl-london-zoo/londonzootickets",
                    R.drawable.lz,R.drawable.zoo);


            insertplace(db, "02 Arena"
                    ,"The O2 Arena (temporarily the sponsor neutral \"North Greenwich Arena\", during the 2012 Summer Olympics and 2012 Summer Paralympics), is a multipurpose indoor arena located in the centre of The O2 entertainment complex on the Greenwich Peninsula in south east London.\n" +
                            "\nThe arena was built under the former Millennium Dome, a large dome shaped building built to house an exhibition celebrating the turn of the third millennium; as the dome shaped structure still stands over the arena, The Dome remains a name in common usage for the venue. The arena, as well as the total O2 complex, is named after its primary sponsor, the telecommunications company O2.\n" +
                            "\nThe O2 Arena has the second highest seating capacity of any indoor venue in the United Kingdom, behind the Manchester Arena, but took the crown of the world's busiest music arena from New York City's Madison Square Garden in 2008. The closest underground station to the venue is the North Greenwich station on the Jubilee line.\n"
                    ,"North Greenwich Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.503146
                    ,0.003159
                    ,"free"
                    ,"free"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"https://www.theo2.co.uk/visit-us",
                    R.drawable.newzero_two,R.drawable.shopszo);


            insertplace(db, "Hamleys Toy Store"
                    ,"Hamleys is the oldest and largest toy shop in the world and one of the world's best-known retailers of toys. Founded by William Hamley as \"Noahs Ark\" in High Holborn, London, in 1760, it moved to its current site on Regent Street in 1881. This flagship store is set over seven floors, with more than 50,000 lines of toys on sale. It is considered one of the city's prominent tourist attractions, receiving around five million visitors each year. The chain has ten other outlets in the United Kingdom and also has more than 60 franchises worldwide."
                    ,"Oxford Circus Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.512787
                    ,-0.140165
                    ,"free"
                    ,"free"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"http://www.hamleys.com/",
                    R.drawable.ham,R.drawable.store);


            insertplace(db, "Tower Bridge"
                    ,"Tower Bridge is a combined bascule and suspension bridge in London built between 1886 and 1894. The bridge crosses the River Thames close to the Tower of London and has become an iconic symbol of London, resulting in it sometimes being confused with London Bridge, situated some 0.5 mi (0.80 km) upstream. Tower Bridge is one of five London bridges now owned and maintained by the Bridge House Estates, a charitable trust overseen by the City of London Corporation. It is the only one of the Trust's bridges not to connect the City of London directly to the Southwark bank, as its northern landfall is in Tower Hamlets.\n" +
                            "\nThe bridge consists of two bridge towers tied together at the upper level by two horizontal walkways, designed to withstand the horizontal tension forces exerted by the suspended sections of the bridge on the landward sides of the towers. The vertical components of the forces in the suspended sections and the vertical reactions of the two walkways are carried by the two robust towers. The bascule pivots and operating machinery are housed in the base of each tower. Before its restoration in the 2010s, the bridge's colour scheme dated from 1977, when it was painted red, white and blue for Queen Elizabeth II's Silver Jubilee. Its colours were subsequently restored to blue and white.\n" +
                            "\nThe bridge deck is freely accessible to both vehicles and pedestrians, whereas the bridge's twin towers, high-level walkways and Victorian engine rooms form part of the Tower Bridge Exhibition, for which an admission charge is made. The nearest London Underground tube stations are Tower Hill on the Circle and District lines, London Bridge on the Jubilee and Northern lines and Bermondsey on the Jubilee line, and the nearest Docklands Light Railway station is Tower Gateway. The nearest National Rail stations are at Fenchurch Street and London Bridge.\n"
                    ,"Tower Hill Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.507880
                    ,-0.087732
                    ,"£9.80"
                    ,"£6.80"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"https://www.gammabookings.com/TowerBridgeBookings/booktickets.aspx?eid=0005",
                    R.drawable.londonb,R.drawable.lb);


            insertplace(db, "The House Of Parliament","Ten the hastened steepest feelings pleasant few surprise property. An brother he do colonel against minutes uncivil. Can how elinor warmly mrs basket marked. Led raising expense yet demesne weather musical. Me mr what park next busy ever. Elinor her his secure far twenty eat object. Late any far saw size want man. Which way you wrong add shall one. As guest right of he scale these. Horses nearer oh elinor of denote."
                    ,"Westminster Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.500613
                    ,-0.126069
                    ,"£28"
                    ,"£12"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"http://www.parliament.uk/visiting/visiting-and-tours/",
                    R.drawable.bigben,R.drawable.house_pic);


            insertplace(db, "Buckingham Palace"
                    ,"Ten the hastened steepest feelings pleasant few surprise property. An brother he do colonel against minutes uncivil. Can how elinor warmly mrs basket marked. Led raising expense yet demesne weather musical. Me mr what park next busy ever. Elinor her his secure far twenty eat object. Late any far saw size want man. Which way you wrong add shall one. As guest right of he scale these. Horses nearer oh elinor of denote."
                    ,"Westminster Station"
                    ,"St Katharine's & Wapping, London EC3N 4AB"
                    ,51.501365
                    ,-0.141889
                    ,"free"
                    ,"free"
                    ,"Made last it seen went no just when of by. Occasional entreaties comparison me difficulty so themselves. At brother inquiry of offices without do my service. As particular to companions at sentiments. Weather however luckily enquire so certain do. Aware did stood was day under ask. Dearest affixed enquire on explain opinion he. Reached who the mrs joy offices pleased. Towards did colonel article any parties."
                    ,"Tue - Sat\n9am - 4:30am\n\nSun - Mon\n10am - 4:30"
                    ,"https://tickets.royalcollection.org.uk/",
                    R.drawable.buckingham_palace_two,R.drawable.bp);

            // template
            //   insertDrink(db, "Totally Thames Festival", "", "1 Mar 17","","","", R.drawable.thames_fest);

        }
        if (oldVersion < 2) {// add extra collum called
            db.execSQL("ALTER TABLE Event ADD COLUMN FAVORITE NUMERIC;");

        }
    }
}