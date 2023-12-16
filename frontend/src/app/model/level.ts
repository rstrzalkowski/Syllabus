export interface LevelPage {
  content: Level[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Level {
  id: string
  createdAt: Date
  updatedAt: Date
  value: number
  activeSchoolClasses: number
  archived: boolean
}
