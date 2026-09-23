local stock = redis.call('GET', KEYS[1])
local argument = tonumber(ARGV[1])

if stock and tonumber(stock) >= argument then
    redis.call('DECRBY', KEYS[1], argument)
    return 1
else
    return 0
end