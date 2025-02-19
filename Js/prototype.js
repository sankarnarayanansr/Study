// unsimilar to oops languages 
// we create a template called a class , then 
// we create objects from the class how may ever objects 
// needed using a new and constructor
// In js under the hood there is nothing called a class
// everything is done using prototype , prototypes refer to 
// the template of anything in js , can be object , function , array anything 
// everything is considered as an object and everything has a prototype
// we could directly change the protoype of the object and it will reflect
// in all the objects created from that prototype 

const { get } = require("http");

function genericConsole(){
    
    this.name = 'Generic Console';
    this.price = 100;
}

// declaring static methods and vars 
genericConsole.price = 10
genericConsole.pricefunc = function () {
    console.log('Price is ' + this.price);
}


var gs = new genericConsole()
console.log(gs.name)
genericConsole.pricefunc()

// plain js obj
var obj = {
    x: 10,
    getx: function () {
        return this.x;
    }
}

// this is a plain object and it doesnt have prototype 
// if you want to make dynamic changes to this object
// convert this to a function or a class
function objtemplate(x,y){
   
    this.x = 10;
    this.getx = function(){
        return this.x;
    }
}

var x = new objtemplate(10,20)
objtemplate.prototype.y = 20
console.log(x.y)



//generally we can write a class like this
// these are es6 syntax sugar coated version of the above function
class genericConsole2{
    constructor(){
        this.name = 'Generic Console';
        this.price = 100;
    }
    pricefunc(){
        console.log('Price is ' + this.price);
    }
}
