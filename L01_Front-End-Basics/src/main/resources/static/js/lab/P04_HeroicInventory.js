function heroicInventory(args) {
    let result = [];

    args.map(heroInput => {
        let [name, level, items] = heroInput.split(' / ')
        result.push({name, level: Number(level), items: items ? items.split(', ') : []})
    })

    console.log(JSON.stringify(result));
}

heroicInventory(['Isacc / 25 / Apple, GravityGun',
    'Derek / 12 / BarrelVest, DestructionSword',
    'Hes / 1 / Desolator, Sentinel, Antara']);

heroicInventory(['Jake / 1000 / Gauss, HolidayGrenade']);

